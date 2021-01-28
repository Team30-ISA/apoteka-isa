/*var app = new Vue({
	el: '#profile',
	data: {
		patients: null,
		oldpass: "",
		newpass: "",
		name: "",
		surname: "",
	},
	methods: {
		changepass() {
		axios.get('/api/patient/updatePassword',{
		headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  },
		params:{
			oldpass: this.oldpass,
			newpass: this.newpass,
			id: this.patients.id,
			}
		}).then(response => {
					alert('Lozinka je uspesno promenjena.')
			})
		},
		changedata(){
		axios.get('/api/patient/updatePatient',{
		headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  },
		params:{
			name: this.name,
			surname: this.surname,
			id: this.patients.id,
			}
		}).then(response => {
					alert('Novo ime: ' + this.name + '\nNovo prezime: ' + this.surname)
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
					this.patients = response.data
					this.name = this.patients.firstName
					this.surname = this.patients.lastName
			})
		
	}
})*/

var app = new Vue({
	el: '#patientProfile',
	data: {
		patient: null,
		changePass: false,
		changeData: false,
		oldPass: "",
		newPass: "",
		repeatPass: "",
		name: "",
		surname: "",
		email: "",
		countries: null,
		cities: null,
		addresses: null,
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
		changeState(){
			if(this.changePass == true){
				this.changePass = false;
				//axios za izmenu passworda
				axios.get('/api/patient/updatePassword',{
					headers: {
				    	'Authorization': "Bearer " + localStorage.getItem('access_token')
			  		},
					params:{
						oldPass: this.oldPass,
						newPass: this.newPass,
						id: this.patient.id,
					}
				}).then(response => {
					alert('Lozinka je uspesno promenjena.')
				})
			}
			else
				this.changePass = true;
			},
		discardPassCh(){
			this.changePass = false;
			this.oldPass = "";
			this.newPass = "";
			this.repeatPass = "";
			
		},
		chData(){
			if(this.changeData == true){
				this.changeData = false;
				//axios za izmenu podataka
				axios.get('/api/patient/updatePatient',{
		headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  },
		params:{
			name: this.name,
			surname: this.surname,
			email: this.email,
			id: this.patient.id,
			}
		}).then(response => {
					alert('Novo ime: ' + this.name + '\nNovo prezime: ' + this.surname + '\nNov email: ' + this.email)
			})
				}
			else{
				this.changeData = true;
			}
				
		},
		discardDataCh(){
			this.changeData = false;
			axios
			.get('/api/patient/getLoggedUser',{
				  headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  }
		     })
		     .then(response => {
		     	this.patient = response.data
		     })
		},
		
		findCity(){
			axios
			.get('/api/city/getAllCitiesForCountry',{
				headers: {
				 'Authorization': "Bearer " + localStorage.getItem('access_token')
				},
				params:{
					id: this.selectedCountry.id,
				}
	     })
	     .then(response => {
	     	console.log(response.data)
	     	this.cities = response.data	
	     })
		},
		findAddresses(){
			axios
			.get('/api/address/getAllAddressesForCity',{
				headers: {
				 'Authorization': "Bearer " + localStorage.getItem('access_token')
				},
				params:{
					id: this.selectedCity.id,
				}
	     })
	     .then(response => {
	     	console.log(response.data)
	     	this.addresses = response.data	
	     })
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
        	if(response.data != "PATIENT"){
        		window.location.href = '/login.html';
        	}
        })
        .catch(function() {
        	window.location.href = '/login.html';
	    })
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.patient = response.data
	     	this.name = this.patient.firstName
	     	this.surname = this.patient.lastName
	     	this.email = this.patient.email
	     	
	     })
	     axios
		.get('/api/country/getAllCountries',{
			headers: {
				 'Authorization': "Bearer " + localStorage.getItem('access_token')
			}
	     })
	     .then(response => {
	     	console.log(response.data)
	     	this.countries = response.data
	     })
	}
})