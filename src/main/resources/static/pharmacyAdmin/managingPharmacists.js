var app = new Vue({
	el: '#managePharm',
	data: {
		pharms: [],
		pharmacyId: "",
		admin: null,
		changePass: false,
		changeData: false,
		oldPass: "",
		newPass: "",
		newDerms: [],
		pharmId: null,
		newPharm: null,
		countries: [],
		cities: []
		
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
		
		dermCal(dermId){
			window.localStorage.setItem('dermId', dermId);
		},
		
		
		
		firePharm(pharmId){
			window.localStorage.setItem('pharmId', pharmId);
			this.pharmId = window.localStorage.getItem('pharmId');
			axios
	        .delete('/api/pharmacist/fire/' + pharmId,{

	    		headers: {
					'Authorization': "Bearer " + localStorage.getItem('access_token'),
				    "Content-Type": "application/json"
				  }
				  
	        })
	        .then(response => {
	            
	        })
		},
		
		changeState(){
			if(this.changePass == true){
				this.changePass = false;
				axios
		        .post('/api/pharmacist/save',
		        	
		        	{
		        		firstName: this.newPharm.firstName,
		        		lastName: this.newPharm.lastName,
		        		email: this.newPharm.email,
		        		address: this.newPharm.address.street,
		        		cityId: this.selectedCity.id,
		        		username: this.newPharm.username
		            },{
		        	
		    		headers: {
						'Authorization': "Bearer " + localStorage.getItem('access_token'),
					    "Content-Type": "application/json"
					  }
					  
		        })
		        .then(response => {
	        		JSAlert.alert("You have successfully registered a new pharmacist!");
	        		axios
					.get('/api/pharmacy/findAllPharmsInPharmacy',
							{
								params:{
									id: this.pharmacyId
								},
							headers: {
							    'Authorization': "Bearer " + localStorage.getItem('access_token')
							  }
						
					})
					.then(response => {
						this.pharms = response.data
					})
		            
		        })
		        .catch(error => {
		            console.log(error)
		            if (error.response.status == 401 || error.response.status == 400 || error.response.status == 500) {
		                JSAlert.alert("Fields cannot be empty. Please try again.");
		                axios
		        		.get('/api/pharmacyAdmin/getLoggedUser',{
		        			  headers: {
		        				    'Authorization': "Bearer " + localStorage.getItem('access_token')
		        			  }
		        	     })
		        	     .then(response => {
		        	     	this.admin = response.data
		        	     })
		            } 
		            
		        })
			}
			else
				this.changePass = true;
		},
		discardPassCh(){
			this.changePass = false;
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
			.get('/api/pharmacyAdmin/getPharmacy',{
				  headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  }
		     })
		     .then(response => {
		    	 this.pharmacyId = response.data
		    	 axios
					.get('/api/pharmacy/findAllPharmsInPharmacy',
							{
								params:{
									id: this.pharmacyId
								},
							headers: {
							    'Authorization': "Bearer " + localStorage.getItem('access_token')
							  }
						
					})
					.then(response => {
						this.pharms = response.data
					})
		     }),
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
								id: this.admin.address.city.id,
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