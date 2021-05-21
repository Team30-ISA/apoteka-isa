new Vue({
  el: "#defineLoyalty",
  data: {
	admin: null,
    type: "",
	counselingPoints: "",
    pointsExamination: "",
	discount: "",
	minPoints: "",
	types: ["REGULAR", "SILVER", "GOLD"]
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
    async createLoyalty() {
      try {
        await axios.post(
          "/api/loyalty/define",
          {
            type: this.type,
            counselingPoints: this.counselingPoints,
			examinationPoints: this.examinationPoints,
			minPoints: this.minPoints,
			discount: this.discount
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        JSAlert.alert("Successfully defined loyalty program.");
		window.location.href = "/sysAdmin/manageLoyaltyProgram.html";
      } catch (error) {
        console.log(error);
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
  }
});
