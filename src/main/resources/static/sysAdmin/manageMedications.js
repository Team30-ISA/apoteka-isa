new Vue({
  el: "#manageMedications",
  data: {
	admin: null,
    name: "",
    type: "",
    form: "",
    contraindications: "",
    composition: "",
    recommendedIntakePerDay: "",
    manufacturer: "",
	medicationName: "",
    forms: [],
    types: [],
    medicines: [],
    substitutes: [],
	medications: [],
	allMedicines: [],
	columns: ['name', 'city'],
	columns2: ['name', 'manufacturer'],
	tableh: ['Name', 'City'],
	tableh2: ['Name', 'Manufacturer'],
	sortKey: 'price',
	reverse: 1,
	loyaltyPoints: 1,
	code: null
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
	sortBy(sortKey) {
      		this.reverse = -this.reverse;
      		this.rev = !this.rev;
      		this.sortKey = sortKey;
      		console.log(this.reverse)
      		console.log(this.sortKey)
    	},
	searchMedication(){
			console.log(this.medicationName)
			if(this.medicationName == ""){
				axios
				.get('/api/medicine/getAll',{
					headers: {
						    			'Authorization': "Bearer " + localStorage.getItem('access_token')
					  			},
					}).then(response => {
						this.allMedicines = response.data
						console.log(this.allMedicines)
				})
			}
			else{
				axios
				.get('/api/medicine/searchMedicinesByName',{
					headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  	},
				  	params: {
				  		name: this.medicationName,
				  	}
				})
				.then(response => {
					this.allMedicines = response.data
					console.log(response.data)
				})
			}
	},
    async createMedicine() {
      try {
			if(this.name != "" && this.type != ""  && this.code != "" && this.code != null && this.code != undefined && this.form != "" && this.contraindications != "" && this.composition != "" && this.recommendedIntakePerDay != "" && this.manufacturer != ""){
			if(this.code > 0){
				await axios
				.get('/api/medicine/searchMedicinesByName',{
					headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  	},
				  	params: {
				  		name: this.name
				  	}
				})
				.then(response => {
					this.medications = response.data
					console.log(response.data)
				})
		var k;
		for (var key in this.medications) {
			  console.log(key, this.medications[key].name);
			  k = key;
		}
		console.log(this.loyaltyPoints)
		console.log(this.code)
		
			let con = true;
			let str = String(this.name);
			console.log(str)
			for( let i = 0; i < str.length; i++){
				if(!isNaN(str.charAt(i))){           
					str = false;
					continue;
				}
			}
			
			if(!str){
			JSAlert.alert("Medicine name must contain only letters.");
			con = false;
			this.name = "";	
			}
		
	    if (k === undefined){
			if(this.loyaltyPoints !== null){
				if(con){
        await axios.post(
          "/api/medicine",
          {
            name: this.name,
            type: this.type,
            form: this.form,
            contraindications: this.contraindications,
            composition: this.composition,
            recommendedIntakePerDay: this.recommendedIntakePerDay,
            substitutes: this.substitutes,
            manufacturer: this.manufacturer,
			loyaltyPoints: this.loyaltyPoints,
			code : this.code
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        ).then(response => {
            	 JSAlert.alert("Successfully added new drug");
				 setTimeout(function () {
                window.location.href = "/sysAdmin/manageMedications.html";
              }, 3000);
            })
            .catch(error => {
            	if(error.response.status == 400)
            		JSAlert.alert("Code must have unique, for example 1010");
             });
				}
			}else{
				JSAlert.alert("points must have positive number!");
			}
		}
		else{
			JSAlert.alert("This medicine have in pharmacy!");
			this.name = "";
			return
		}
				}else{
					JSAlert.alert("Code must have positive number!");
				}
		}
		else{
			JSAlert.alert("You haven't filled in all the fields!");
		}
      } catch (error) {
			JSAlert.alert("Code must have unique!");
      }
    }
  },
  async created() {
    try {
      let resp = await axios.get("/api/medicine/types", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.types = resp.data;
      resp = await axios.get("/api/medicine/forms", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.forms = resp.data;
      resp = await axios.get("/api/medicine/findAllMedicine", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.medicines = resp.data;
	  axios
			.get('/api/medicine/getAll',{
			headers: {
				    			'Authorization': "Bearer " + localStorage.getItem('access_token')
			  			},
			}).then(response => {
				this.allMedicines = response.data
				console.log(this.allMedicines)
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
	  
    } catch (err) {
      console.log(err);
      }
  }
});
