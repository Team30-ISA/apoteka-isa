var app = new Vue({
  el: "#promotions",
  data: {
	patient: null,
    pharmacies: [],
	pharmaciesSub: []
  },
  methods: {
    async checkRole() {
      axios
        .get("/auth/getRole", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token")
          }
        })
        .then((response) => {
          this.role = response.data;
          if (response.data != "PATIENT") {
            window.location.href = "/login.html";
          }
        })
        .catch(function () {
          window.location.href = "/login.html";
        });
    },
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
    async unsubscribe(id) {
      await axios.post("/api/promotion/unsubscribe", id, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token"),
          "Content-Type": "application/json"
        }
      }).then(response => {
				if(response.status == 400){
					console.log(response.status)
				}else{
					JSAlert.alert("You have successfully unsubscribed for pharmacy promotions!");
					console.log(response.status)
				}
				
		}).catch((error) => {
          console.log(error);
		  console.log(error.response.status);
          if (error.response.status == 400) {
            JSAlert.alert("Sorry, you already unsubscribed to the pharmacy!");
          }
        });
		
      const { data } = await axios.get("/api/promotion", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.pharmacies = data;
	  console.log(this.pharmacies)
    },
	async subscribe(id) {
		await axios
		    .post("/api/promotion/subscribe", id, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token"),
          "Content-Type": "application/json"
        }
      }).then(response => {
				if(response.status == 400){
					console.log(response.status)
				}else{
					JSAlert.alert("You have successfully subscribed for pharmacy promotions!");
					console.log(response.status)
				}
				
		}).catch((error) => {
          console.log(error);
		  console.log(error.response.status);
          if (error.response.status == 400) {
            JSAlert.alert("Sorry, you already subscribed for pharmacy promotions!");
          }
        });
	    axios
			.get('/api/pharmacy/findAll',{
			headers: {
				    			'Authorization': "Bearer " + localStorage.getItem('access_token')
			  			},
			}).then(response => {
				this.pharmaciesSub = response.data
				console.log(this.pharmaciesSub)
			})	
    }
  },
  async created() {
    await this.checkRole();

    try {
      const { data } = await axios.get("/api/promotion", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });

      this.pharmacies = data;
	  
	  await axios
			.get('/api/pharmacy/findAll',{
			headers: {
				    			'Authorization': "Bearer " + localStorage.getItem('access_token')
			  			},
			}).then(response => {
				this.pharmaciesSub = response.data
				console.log(this.pharmaciesSub)
			})
			
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	    .then(response => {
	     	this.patient = response.data;
		})	
	  
    } catch (err) {
      console.log(err);
    }
	
  }
});
