var app = new Vue({
  el: "#medicine",
  data: {
	admin: null,
    medicineName: "",
    medicines: [],
    spec: null,
    showSpec: false,
    minGrade: 0,
    allMedicines: [],
	listAllMedicines: []
  },
  methods: {
	logout(){
			axios
	        .post('/auth/logout', null, {
				  headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
					  }
		        })
	        .then(function() {
	        	localStorage.clear();
	        	window.location.href = '/login.html';
	        })
	},
    async search() {
      if (this.medicineName) {
        const { data } = await axios.get(
          `/api/medicine/allMedicine/${this.medicineName}`
        );
		let uslov = false;
		for(var key in this.listAllMedicines){
			
			console.log(this.listAllMedicines[key].name)
			
			if(data.length == 0 && this.medicineName == this.listAllMedicines[key].name && this.listAllMedicines[key].price == undefined && this.listAllMedicines[key].price == null){
					uslov = true;
					continue
				}	
		}
		
		if(uslov === true){
			JSAlert.alert("Medicine exist in system, but pharmacy administrator must define price, before users can buy.")
			this.medicineName = ""
		}
		
		if(!uslov && data.length == 0)
		{
			JSAlert.alert("Medicine doesn't exist in pharmacy system!")
			setTimeout(function () {
                window.location.href = "/pharmacyAdmin/medicine.html";
              }, 2000);
		}
        this.allMedicines = data;
		this.medicines = data.filter((m) => m.pharmacy.grade >= this.minGrade || (m.medicine.form.name === this.filtForm && m.medicine.type.name === this.filtType));

      }
    },
    async getSpec(id) {
      const { data } = await axios.get(`/api/medicine/${id}`);
      this.showSpec = true;
      this.spec = data;
    },
    closeSpec() {
      this.showSpec = false;
      this.spec = null;
    }
  },
  watch: {
    minGrade() {
      if (this.allMedicines)
        this.medicines = this.allMedicines.filter(
          (m) => m.pharmacy.grade >= this.minGrade
        );
    }
  },
  created() {
		axios
        .get('/auth/getRole',{
			  headers: {
			    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
        })
        .then(response => {
        	if(response.data != "ADMIN"){
        		window.location.href = '/login.html';
        	}
        })
        .catch(function() {
        	window.location.href = '/login.html';
	    })
		axios
		.get('/api/pharmacyAdmin/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.admin = response.data
	     })
		 
		 axios
			.get('/api/medicine/getAll',{
			headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  			},
			}).then(response => {
				this.listAllMedicines = response.data
				console.log(this.listAllMedicines)
			});
  }
});
