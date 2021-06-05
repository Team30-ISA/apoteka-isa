var app = new Vue({
  el: "#complaints",
  data: {
	patient: null,
    tab: "DERMATOLOGIST",
    pharmacies: [],
    dermatologists: [],
    pharmacists: [],
    complaint: "",
    selected: {
      PHARMACIST: "",
      DERMATOLOGIST: "",
      PHARMACY: ""
    }
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
    async sendComplaint() {
      try {
		if(this.complaint != "" && this.complaint != null && this.complaint != undefined && this.tab != "" && this.tab != null && this.tab != undefined && this.selected.id != null && this.selected.id != "" && this.selected.id != undefined ){
        await axios.post(
          "/api/complaint",
          {
            recipient: this.selected.id,
            complaintUser: this.tab,
            message: this.complaint,
            recipientName:
              this.tab != "PHARMACY"
                ? this.selected.firstname + " " + this.selected.lastname
                : this.selected.name
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        JSAlert.alert("Complaint created successfully!");
		setTimeout(function () {
                window.location.href = "/patient/complaint.html";
              }, 3000);
		}else{
			JSAlert.alert("You must select tab and input complaint before sent!");
		}
      } catch (err) {
        console.log(err);
      }
    }
  },
  async created() {
    axios
      .get("/auth/getRole", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      })
      .then((response) => {
        if (response.data != "PATIENT") {
          window.location.href = "/login.html";
        }
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	    .then(response => {
	     	this.patient = response.data;
		})
      })
      .catch(function () {
        window.location.href = "/login.html";
      });
    let { data } = await axios.get("/api/patient/complaint-data", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("access_token")
      }
    });
    console.log(data);
    this.pharmacies = data.pharmacies;
    this.dermatologists = data.dermatologists;
    this.pharmacists = data.pharmacists;
  },
  watch: {
    tab() {
      this.complaint = "";
      this.selected = "";
    }
  }
});
