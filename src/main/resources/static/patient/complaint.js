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
                window.location.href = "http://localhost:8081/patient/complaint.html";
              }, 3000);
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
  },
	created(){
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	    .then(response => {
	     	this.patient = response.data;
		})
	}
});
