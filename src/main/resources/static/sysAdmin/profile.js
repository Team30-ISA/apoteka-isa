var app = new Vue({
  el: "#sysAdminProfile",
  data: {
	admin:null,
    user: null,
    changeData: false,
    oldPass: "",
    newPass: "",
    repeatPass: "",
    countries: null,
    cities: null,
    addresses: null,
    idCountry: 0,
    idCity: 0,
    idAddress: 0
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
    changeState() {
      window.location.href = "/changePassword.html";
    },
    discardPassCh() {
      this.changePass = false;
      this.oldPass = "";
      this.newPass = "";
      this.repeatPass = "";
    },
    chData() {
      if (this.changeData == true) {
        this.changeData = false;
        //axios za izmenu podataka
        const user = {
          ...this.user,
          address: {
            ...this.user.address,
            city: {
              ...this.cities.find((c) => this.selectedCity.id === c.id)
            }
          }
        };
		
			let con = true;
			let str = String(this.user.firstName);
			for( let i = 0; i < str.length; i++){
				if(!isNaN(str.charAt(i))){           
					str = false;
					continue;
				}
			}
			
			if(!str){
			JSAlert.alert("First name must contain only letters.");
			con = false;
			this.user.firstName = "";	
			}
			
			let con1 = true;
			let str1 = String(this.user.lastName);
			for( let i = 0; i < str1.length; i++){
				if(!isNaN(str1.charAt(i))){           
					str1 = false;
					continue;
				}
			}
			
			if(!str1){
				JSAlert.alert("Last name must contain only letters.");
				con1 = false;
				this.user.lastName = "";			
			}
			
			let con2 = true;
			if(isNaN(this.user.phonenumber) || this.user.phonenumber < 0 ){
				JSAlert.alert("The phone number must consist only of digits.")
				con2 = false;
				this.user.phonenumber = "";
			}
				
		if(con && con1 && con2){
        axios
          .put(
            "/api/sys-admin",
            {
              ...this.user,
              address: this.user.address.street,
              cityId: this.selectedCity.id,
              stateId: this.selectedCountry.id,
              firstname: this.user.firstName,
              lastname: this.user.lastName,
			  phoneNumber: this.user.phonenumber
            },
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token")
              }
            }
          )
          .then((response) => {
            localStorage.setItem("user", JSON.stringify(user));
            this.user = user;
            JSAlert.alert("You have successfully changed your details.");
          });
		}
      } else {
        this.changeData = true;
      }
    },
    discardDataCh() {
      this.user = JSON.parse(localStorage.getItem("user"));
      this.changeData = false;
    },
    findCity() {
      axios
        .get("/api/city/getAllCitiesForCountry", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token")
          },
          params: {
            id: this.selectedCountry.id
          }
        })
        .then((response) => {
          this.cities = response.data;
        });
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
    const user = JSON.parse(localStorage.getItem("user"));
    this.user = user;
    this.selectedCity = user.address.city.id;
    this.selectedCountry = user.address.city.country.id;
    axios
      .get("/api/country/getAllCountries", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      })
      .then((response) => {
        this.countries = response.data;

        axios
          .get("/api/city/getAllCitiesForCountry", {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            },
            params: {
              id: user.address.city.country.id
            }
          })
          .then((response) => {
            this.cities = response.data;
          });
      });
  }
});
