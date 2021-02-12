new Vue({
  el: "#defineLoyalty",
  data: {
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
  }
});
