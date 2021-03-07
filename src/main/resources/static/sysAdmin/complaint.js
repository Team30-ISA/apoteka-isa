var app = new Vue({
  el: "#complaints",
  data: {
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
        .catch(function (err) {
          console.log(err);
        });
		JSAlert.alert("The response to the complaint was sent successfully!");
		setTimeout(function () {
                window.location.href = "http://localhost:8081/sysAdmin/complaint.html";
              }, 3000);
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
