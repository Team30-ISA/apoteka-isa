var app = new Vue({
	el: '#pharmacies',
	data: {
		counselings: [],
		derm: [],
		patient: null,
		columns: [ 'id', 'startDate', 'duration'],
		sortKey: 'price',
		reverse: 1,
	},
	methods: {
        sortBy(sortKey) {
      		this.reverse = -this.reverse;
      		this.rev = !this.rev;
      		this.sortKey = sortKey;
      		console.log(this.reverse)
      		console.log(this.sortKey)
    	},
    	btnClick(btnId){
    		console.log(btnId)
			axios
				.get('/api/examination/cancelAppointment',{
			  		headers: {
				    	'Authorization': "Bearer " + localStorage.getItem('access_token')
			  		},
			  		params: {
			  			//patId: this.patient.id,
			  			examId: btnId,
			  		}
	     }).then(response => {
					console.log(response.data)
	     			if(response.data == true){
						alert('Uspesno je otkazan pregled')
						window.location.reload();
					}
					else
						alert('Neuspesno je otkazan pregled, do pregleda je ostalo manje od 24 sata')
			}).catch(error => {
	            if (error.response.status == 401 || error.response.status == 400 || error.response.status == 500) {
	                alert('Uspesno je otkazan pregled')
	            }		            
	        })
    		
    	}
	},
	created() {
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     }).then(response => {
					this.patient = response.data
					this.pId = response.data.id
					console.log(this.patient.id)
					
					axios
						.get('/api/examination/getExaminationsForPatient',{
			  				headers: {
				    			'Authorization': "Bearer " + localStorage.getItem('access_token')
			  			},
	     }).then(response => {
					this.counselings = response.data
					console.log(response.data)
			})
			})
		


	}
})