new Vue({
  el: "#manageUsers",
  data: {
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    phonenumber: "",
    city: "",
    country: "",
    addressString: "",
    countryOptions: [],
    cityOptions: [],
    selectedUserType: "ROLE_SYS_ADMIN",
	user: []
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
    async createUser() {
      try {
		user = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        username: this.email,
        password: this.password,
        phonenumber: this.phonenumber,
        cityId: this.city.id,
        stateId: this.city.country.id,
        addressString: this.addressString
      };
	  }catch (err) {
        console.log(err);
        JSAlert.alert("You haven't filled in all the fields!");
      }
      try {
		if(this.firstName != "" && this.lastName != "" && this.email != "" && this.username != "" && this.password != "" && this.phonenumber != "" && this.addressString != ""){
        if(isNaN(this.phonenumber) || !this.phonenumber){
			JSAlert.alert("The phone number must consist only of digits!");
			return;
		}else if (this.password.length < 5) {
			JSAlert.alert("Password must have minimal 6 characters!");
			this.password = "";
			return;
		}
			else{
		switch (this.selectedUserType) {
          case "ROLE_SYS_ADMIN":
            await axios.post("/api/sys-admin", user, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token")
              }
            });
            break;
          case "ROLE_DERM":
            await axios.post("/api/sys-admin/derm", user, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token")
              }
            });
            break;
          case "ROLE_SUPL":
            await axios.post("/api/sys-admin/suppl", user, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token")
              }
            });
            break;
			}
        
        JSAlert.alert("User successfully created.");
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.phonenumber = "";
        this.city = "";
        this.country = "";
        this.addressString = "";
		}
		}else{
			JSAlert.alert("You haven't filled in all the fields!");
		}
      } catch (err) {
        console.log(err);
        JSAlert.alert("User with given email already exists.");
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
      })
      .catch(function () {
        window.location.href = "/login.html";
      });
    const res = await axios.get("/api/country/getAllCountries");
    this.countryOptions = res.data;
  },
  watch: {
    country: async function (country) {
      this.cityOptions = "";
      const { data } = await axios.get("/api/city/getAllCitiesForCountry", {
        params: {
          id: country.id
        }
      });
      this.cityOptions = data;
    }
  }
});
