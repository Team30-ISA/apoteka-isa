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
		if(data.length == 0)
		{
			JSAlert.alert("Medicine doesn't exist!")
			setTimeout(function () {
                window.location.href = "/sysAdmin/medicine.html";
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
  created(){
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
