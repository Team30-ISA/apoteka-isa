var app = new Vue({
	el: '#registrationPatient',
	data: {
		patient: null,
		username: "",
		firstName: "",
		lastName: "",
		email: "",
		password: "",
		repeatPassword: "",
		phone: "",
		info: null,
		countries: null,
		cities: null,
		selC: null
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
		registration(){
			if(this.changeData == true){
				this.changeData = false;
				if(this.patient.firstName != "" && this.patient.lastName != "" && this.patient.address != ""){
		     	axios
		        .post('/api/patient/save',
		        	
		        	{
		        		firstName: this.patient.firstName,
		        		lastName: this.patient.lastName,
		        		street: this.patient.address.street,
		        		cityId: this.selectedCity.id
		            },{
		        	
		    		headers: {
						'Authorization': "Bearer " + localStorage.getItem('access_token'),
					    "Content-Type": "application/json"
					  }
					  
		        })
		        .then(response => {
	        		JSAlert.alert("You have successfully registered patient.");
	        		axios
	        		.get('/api/patient/getLoggedUser',{
	        			  headers: {
	        				    'Authorization': "Bearer " + localStorage.getItem('access_token')
	        			  }
	        	     })
	        	     .then(response => {
	        	     	this.patient = response.data
	        	     })
		            
		        })
		        .catch(error => {
		            console.log(error)
		            if (error.response.status == 401 || error.response.status == 400 || error.response.status == 500) {
		                JSAlert.alert("Fields cannot be empty. Please try again.");
		                axios
		        		.get('/api/patient/getLoggedUser',{
		        			  headers: {
		        				    'Authorization': "Bearer " + localStorage.getItem('access_token')
		        			  }
		        	     })
		        	     .then(response => {
		        	     	this.patient = response.data
		        	     })
		            } 
		            
		        })
				}else{
					JSAlert.alert("Fields cannot be empty. Please try again.");
					 axios
		        		.get('/api/patient/getLoggedUser',{
		        			  headers: {
		        				    'Authorization': "Bearer " + localStorage.getItem('access_token')
		        			  }
		        	     })
		        	     .then(response => {
		        	     	this.patient = response.data
		        	     })
				}
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
			console.log
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
	     	this.patient = response.data;
	     	this.selC = this.patient.address.city.country.country;
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
				console.log
				axios
				.get('/api/city/getAllCitiesForCountry',{
					headers: {
					 'Authorization': "Bearer " + localStorage.getItem('access_token')
					},
					params:{
						id: this.patient.address.city.id,
					}
		     })
		     .then(response => {
		     	console.log(response.data)
		     	this.cities = response.data	
		     	console.log(this.cities)
		     })
	     })
	}
})