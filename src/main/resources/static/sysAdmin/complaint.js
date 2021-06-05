var app = new Vue({
  el: "#complaints",
  data: {
	admin: null,
    tab: "DERMATOLOGIST",
    compliants: [],
    selected: "",
    response: ""
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
    async submit() {
	if((this.response != "" && this.response != null && this.response != undefined) && (this.selected.id != "" && this.selected.id != null && this.selected.id != undefined)){
      axios
        .post(
          "/api/sys-admin/answer",
          {
            response: this.response,
            complaintId: this.selected.id
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        )
        .then((response) => {
          this.selected = "";
          this.response = "";
          this.compliants = this.compliants.filter((c) => c.id != selected.id);
        })
		JSAlert.alert("The response to the complaint was sent successfully!");
			setTimeout(function () {
					window.location.href = "/sysAdmin/complaint.html";
				  }, 3000);
    }else{
		JSAlert.alert("You must first select a complaint and only then respond to it, of course if it exists at all.");
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
        if (response.data != "SYS_ADMIN") {
          window.location.href = "/login.html";
        }
		axios
		.get('/api/sys-admin/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.admin = response.data
	     })
      })
      .catch(function () {
        window.location.href = "/login.html";
      });
    let { data } = await axios.get("/api/sys-admin/complaint-data", {
      headers: {
        Authorization: "Bearer " + localStorage.getItem("access_token")
      }
    });
    this.compliants = data;
	
	
  }
});
