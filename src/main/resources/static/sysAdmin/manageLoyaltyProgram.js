new Vue({
  el: "#defineLoyalty",
  data: {
	admin: null,
	user: null,
	loyalty: null,
    changeData: false,
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
	chData() {
      if (this.changeData == true) {
        this.changeData = false;
        
        const loyalty = {
          ...this.loyalty
        };
		
		if(this.loyalty.silverMinPoints > 0 && this.loyalty.goldenMinPoints > 0 && this.loyalty.counselingPoints > 0 && this.loyalty.examinationPoints > 0 && this.loyalty.regularDiscount > 0 && this.loyalty.silverDiscount && this.loyalty.goldenDiscount > 0){
        axios
          .put(
            "/api/loyalty",
            {
              ...this.loyalty
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token")
              }
            }
          )
          .then((response) => {
            this.loyalty = loyalty;
          }).catch(error => {
            	
				JSAlert.alert("You have successfully updated loyalty program.");
			setTimeout(function () {
					window.location.href = "/sysAdmin/manageLoyaltyProgram.html";
				  }, 3000);

        });;
		}else{
			JSAlert.alert("Only positive number input!");
			setTimeout(function () {
					window.location.href = "/sysAdmin/manageLoyaltyProgram.html";
				  }, 2000);
			}
		}
       else {
        this.changeData = true;
      }
    },
    discardDataCh() {
      this.changeData = false;
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
	created() {
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
	  
	 axios
		.get('/api/sys-admin/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.admin = response.data
	     })
	
	  axios
      .get("/api/loyalty", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      })
      .then((response) => {
        this.loyalty = response.data;
		console.log(this.loyalty)
	  })
  }
});
