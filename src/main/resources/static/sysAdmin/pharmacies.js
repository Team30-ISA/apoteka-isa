new Vue({
  el: "#pharmacies",
  data: {
	admin:null,
    pharmacies: [],
    showAddPharmacyForm: false,
    showAddPharmacyAdminForm: false,
    name: "",
    address: "",
    city: "",
    pharmacySelected: false,
    pharmacyAdmins: [],
    email: "",
    password: "",
    firstName: "",
    lastName: "",
    phonenumber: "",
    addressString: "",
    country: "",
    city: "",
    countryOptions: [],
    cityOptions: [],
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
    async submit() {
      try {
		  let con = true;
			let str = String(this.name);
			for( let i = 0; i < str.length; i++){
				if(!isNaN(str.charAt(i))){           
					str = false;
					continue;
				}
			}
			
			if(!str){
			JSAlert.alert("Pharmacy name must contain only letters.");
			con = false;
			this.name = "";	
			}
			
			let con1 = true;
			let str1 = String(this.city);
			for( let i = 0; i < str1.length; i++){
				if(!isNaN(str1.charAt(i))){           
					str1 = false;
					continue;
				}
			}
			
			if(!str1){
				JSAlert.alert("City must contain only letters.");
				con1 = false;
				this.city = "";			
			}
			
		if(con && con1 && this.name != "" && this.address != "" && this.city != ""){
        await axios.post(
          "/api/pharmacy",
          {
		  name: this.name, 
		  address: this.address, 
		  city: this.city 
		  },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        JSAlert.alert("Successfully added new pharmacy");
		
        this.showAddPharmacyForm = false;
        const { data } = await axios.get("/api/pharmacy/findAll", {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token")
          }
        });
        this.pharmacies = data;
		} else{
			JSAlert.alert("You haven't filled in all the fields!");
		}
      } catch (err) {
        JSAlert.alert("Pharmacy with given name or address already exist!");
      }
    },
    async createPharmacyAdmin() {
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
            addressString: this.addressString,
            pharmacyId: this.pharmacySelected
      };
	  }catch (err) {
        console.log(err);
        JSAlert.alert("You haven't filled in all the fields!");
      }
      try {
		 
			let con = true;
			let str = String(this.firstName);
			for( let i = 0; i < str.length; i++){
				if(!isNaN(str.charAt(i))){           
					str = false;
					continue;
				}
			}
			
			if(!str){
			JSAlert.alert("First name must contain only letters.");
			con = false;
			this.firstName = "";	
			}
			
			let con1 = true;
			let str1 = String(this.lastName);
			for( let i = 0; i < str1.length; i++){
				if(!isNaN(str1.charAt(i))){           
					str1 = false;
					continue;
				}
			}
			
			if(!str1){
				JSAlert.alert("Last name must contain only letters.");
				con1 = false;
				this.lastName = "";			
			}
		 
		if(this.firstName != "" && this.lastName != "" && this.email != "" && this.username != "" && this.password != "" && this.phonenumber != "" && this.addressString != ""){
        if(isNaN(this.phonenumber) || this.phonenumber < 0 ){
			JSAlert.alert("The phone number must consist only of digits !");
			return;
		}else if (this.password.length < 6) {
			JSAlert.alert("Password must have minimal 6 characters!");
			this.password = "";
			return;
		} else{
			if(con && con1){
        await axios.post(
          "/api/pharmacyAdmin", 
		  {
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email,
            username: this.email,
            password: this.password,
            phonenumber: this.phonenumber,
            cityId: this.city.id,
            stateId: this.city.country.id,
            addressString: this.addressString,
            pharmacyId: this.pharmacySelected
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
 
        const { data } = await axios.get(
          `/api/pharmacy/${this.pharmacySelected}/admins`,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        this.pharmacyAdmins = data;
        JSAlert.alert("Successfully added new pharmacy admin");
        this.showAddPharmacyAdminForm = false;
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.phonenumber = "";
        this.city = "";
        this.country = "";
        this.addressString = "";
		}
		}
		}else{
			JSAlert.alert("You haven't filled in all the fields!");
		}
      } catch (err) {
        JSAlert.alert("Pharmacy admin with given email already exists!");
      }
    }
  },
  async created() {
    try {
		axios
		.get('/api/sys-admin/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.admin = response.data
	     })
		
		
      const { data } = await axios.get("/api/pharmacy/findAll", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.pharmacies = data;
      const res = await axios.get("/api/country/getAllCountries");
      this.countryOptions = res.data;
    } catch (err) {
      window.location.href = "/login.html";
      console.log(err);
    }
  },
  watch: {
    showAddPharmacyForm() {
      this.name = "";
      this.address = "";
    },
    async pharmacySelected() {
      if (this.pharmacySelected) {
        const { data } = await axios.get(
          `/api/pharmacy/${this.pharmacySelected}/admins`,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        this.pharmacyAdmins = data;
      }
    },
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