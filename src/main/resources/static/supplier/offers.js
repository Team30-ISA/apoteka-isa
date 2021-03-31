var app = new Vue({
  el: "#offers",
  data: {
    filterTypes: ["ALL", "APPROVED", "PENDING", "REFUSED"],
    offers: [],
    errands: [],
    allOffers: [],
    offersTab: true,
    errandPreview: null,
    offerEdit: null,
    stock: [],
    invalidOffer: true,
    supplyDeadline: "",
    price: 1,
    filter: "ALL"
  },
  methods: {
    logout() {
      axios
        .post("/auth/logout", null, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token")
          }
        })
        .then(function () {
          localStorage.clear();
          window.location.href = "/login.html";
        });
    },
    findErrandIndex(id) {
      let ind = false;
      this.errands.forEach((er, i) => {
        if (id === er.id) ind = i;
      });

      return ind;
    },
	formatDate(d){
			let date = new Date(d)
			return date.getDate() + "." + (date.getMonth() + 1) + "." + date.getFullYear() + ".";
		},
    async checkRole() {
      axios
        .get("/auth/getRole", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token")
          }
        })
        .then((response) => {
          if (response.data != "SUPL") {
            window.location.href = "/login.html";
          }
        })
        .catch(function () {
          window.location.href = "/login.html";
        });
    },
    findStockQuantity(id) {
      const medicine = this.stock.find((s) => s.medicineId === id);
      return medicine ? medicine.quantity : 0;
    },
    async submit() {
      try {
		if(this.supplyDeadline<this.errands[this.errandPreview].deadline){
        await axios.post(
          "/api/offer",
          {
            errandId: this.errands[this.errandPreview].id,
            price: this.price,
            supplyDeadline: this.supplyDeadline
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        ).then(response => {
        	JSAlert.alert("Successfully created new offer!");
			this.errandPreview = null;
			this.price = 1;
			this.supplyDeadline = "";
        }).catch(error => {
		            if (error.response.status == 401 || error.response.status == 400 || error.response.status == 500) {
		                JSAlert.alert("Only date in future is accepted!");
		            }		            
		});
		const offers = await axios.get("/api/offer", {
				headers: {
					Authorization: "Bearer " + localStorage.getItem("access_token")
				}
			});
			this.offers = offers.data;
	  }else{
		  JSAlert.alert("Sorry, change offers are possible until the deadline defined by the pharmacy administrator when writing errands!")
	  }
      } catch (err) {
        console.log(err)
		JSAlert.alert("Only date in future is accepted!")
      }
    },
    offerExists() {
      return this.offers.some(
        (o) => o.errandId === this.errands[this.errandPreview].id
      );
    }
  },
  async created() {
    await this.checkRole();
    const errands = await axios.get("/api/offer/erands", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("access_token")
      }
    });
    this.errands = errands.data;
    const offers = await axios.get("/api/offer", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("access_token")
      }
    });
    this.offers = offers.data;
	console.log(this.offers)
    this.allOffers = offers.data;
    const stock = await axios.get("/api/offer/stock", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("access_token")
      }
    });
    this.stock = stock.data;
	console.log(this.stock)
  },
  watch: {
    errandPreview() {
      try {
		  console.log(this.errandPreview)
        if (this.errandPreview !== null) {
          this.invalidOffer = this.errands[
            this.errandPreview
          ].medicineQuantity.some((e) => {
            const med = this.stock.find((s) => s.medicineId === e.medicineId);
            if (!med) return true;
            return e.quantity > med.quantity;
          });
		  console.log(this.invalidOffer)
          const offer = this.offers.find(
            (o) => o.errandId === this.errands[this.errandPreview].id
          );
		  console.log(offer)
          if (offer) {
            this.supplyDeadline = offer.supplyDeadline;
			console.log(new Date())
			console.log(this.supplyDeadline)
            this.price = offer.price;
			console.log(this.price)
          }
        }
      } catch (err) {
        console.log(err);
      }
    },
    async filter() {
      let filterBy;
      switch (this.filter) {
        case "ALL":
          filterBy = "nothing";
          break;
        case "APPROVED":
          filterBy = true;
          break;
        case "PENDING":
          filterBy = null;
          break;
        case "REFUSED":
          filterBy = false;
          break;
      }
      console.log(this.allOffers);
      this.offers = this.allOffers.filter((offer) =>
        filterBy === "nothing" ? true : offer.approved === filterBy
      );
    }
  }
});
