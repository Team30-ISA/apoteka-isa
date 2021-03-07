new Vue({
  el: "#manageMedications",
  data: {
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
	reverse: 1
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
			if(this.name != "" && this.type != "" && this.form != "" && this.contraindications != "" && this.composition != "" && this.recommendedIntakePerDay != "" && this.manufacturer != ""){
				await axios
				.get('/api/medicine/searchMedicinesByName',{
					headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  	},
				  	params: {
				  		name: this.name,
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
		
	    if (k === undefined){
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
            manufacturer: this.manufacturer
          },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        JSAlert.alert("Successfully added new drug");
		
		setTimeout(function () {
                window.location.href = "http://localhost:8081/sysAdmin/manageMedications.html";
              }, 3000);
		}
		else{
			JSAlert.alert("This medicine have in pharmacy!");
			setTimeout(function () {
                window.location.href = "http://localhost:8081/sysAdmin/manageMedications.html";
              }, 3000);
		}
		}
		else{
			JSAlert.alert("You haven't filled in all the fields!");
		}
      } catch (error) {
        console.log(error);
		
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
			})
	  
    } catch (err) {
      console.log(err);
      }
  }
});
