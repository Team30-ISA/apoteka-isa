var app = new Vue({
	el: '#adminProfile',
	data: {
		admin: null,
		changePass: false,
		changeData: false,
		oldPass: "",
		newPass: "",
		repeatPass: "",
		info: null,
		countries: null,
		cities: null,
		selC: null,
		pharmacy: null,
		first: null,
		coords: null,
		ulica: null,
		grad: null,
		pharms: [],
		derms: [],
		medicine: []
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
				if(this.newPass == this.repeatPass){
					axios
			        .post('/auth/change-password',
			            {
							oldPassword: this.oldPass,
							newPassword: this.newPass
			        	},{
			    		headers: {
							'Authorization': "Bearer " + localStorage.getItem('access_token'),
							"Content-Type": "application/json"
						  }
							
			        }).then(response => {
			        	this.info = response.data;
			        	console.log(this.info)
			        	if(this.info.result == 'success'){
			        		JSAlert.alert("You have successfully updated your password!");
			        	}
			            
			        })
			        .catch(error => {
			            console.log(error)
			            if (error.response.status == 401 || error.response.status == 400) {
			                JSAlert.alert("You haven't entered the correct old password!");
			            } 
			            
			        })
				}else{
					JSAlert.alert("New password and confirmed password are not the same!");
				}
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
				if(this.admin.firstName != "" && this.admin.lastName != "" && this.admin.address != ""){
		     	axios
		        .post('/api/pharmacyAdmin/save',
		        	
		        	{
		        		firstName: this.admin.firstName,
		        		lastName: this.admin.lastName,
		        		street: this.admin.address.street,
		        		cityId: this.selectedCity.id
		            },{
		        	
		    		headers: {
						'Authorization': "Bearer " + localStorage.getItem('access_token'),
					    "Content-Type": "application/json"
					  }
					  
		        })
		        .then(response => {
	        		JSAlert.alert("You have successfully updated your profile!");
	        		axios
	        		.get('/api/pharmacyAdmin/getLoggedUser',{
	        			  headers: {
	        				    'Authorization': "Bearer " + localStorage.getItem('access_token')
	        			  }
	        	     })
	        	     .then(response => {
	        	     	this.admin = response.data
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
				}else{
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
				}
			else{
				this.changeData = true;
			}
				
		},
		discardDataCh(){
			this.changeData = false;
			axios
			.get('/api/pharmacyAdmin/getLoggedUser',{
				  headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  }
		     })
		     .then(response => {
		     	this.admin = response.data
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
		},
		init(){ 
		    this.myMap = new ymaps.Map("map", {
		        center: [this.first[1],this.first[0]],
		        zoom: 15
		    });
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
	     	this.admin = response.data;
	     	this.selC = this.admin.address.city.country.country;
	     })
	     axios
		.get('/api/pharmacy/findPharmacyForAdmin',
				{
				headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  }
			
		})
		.then(response => {
			this.pharmacy = response.data
			console.log(this.pharmacy.address)
			console.log(this.pharmacy.city)
			this.ulica = this.pharmacy.address.replace(/\s/g, '+');
			this.grad = this.pharmacy.city.replace(/\s/g, '+');
			console.log(this.ulica)
			console.log(this.ulica)
			axios
			.get("https://geocode-maps.yandex.ru/1.x/?format=json&apikey=3399ed0d-4b2b-455e-aedb-bdf2f1619fc7&lang=sr_Latn_RS&geocode=Subotica+Zetska")
			 .then(response => {
			 	console.log(response.data)
			 	console.log(this.coords = response.data.response.GeoObjectCollection.featureMember[0].GeoObject.Point.pos)
			 	console.log(this.first = this.coords.split(" "))
			 	console.log();
			 	 
			 })
			ymaps.ready(this.init);
			axios
			.get('/api/pharmacy/findAllPharmsInPharmacy',
					{
						params:{
							id: this.pharmacy.id
						},
					headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
					  }
				
			})
			.then(response => {
				this.pharms = response.data
				axios
				.get('/api/pharmacy/findAllDermsInPharmacy',
						{
							params:{
								id: this.pharmacy.id
							},
						headers: {
						    'Authorization': "Bearer " + localStorage.getItem('access_token')
						  }
					
				})
				.then(response => {
					this.derms = response.data;
					axios
					.get('/api/medicine/findAllMedicineAvailableInPharmacy',
							{
								
							headers: {
							    'Authorization': "Bearer " + localStorage.getItem('access_token')
							  }
						
					})
					.then(response => {
						this.medicine = response.data})
				})
			})
		})
		
	}
})