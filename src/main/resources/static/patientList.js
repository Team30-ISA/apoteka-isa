var app = new Vue({
	el: '#patientList',
	data: {
		patients: [],
		searchPharmFirst: "",
		searchPharmLast: "",
		role: "",
		user: null
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
		search(){
			axios
			.get('/api/pharmacy/searchDermsInPharmacy',
					{
						params:{
							id: this.pharmacyId,
							firstName: this.searchPharmFirst,
							lastName: this.searchPharmLast
						},
					headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
					  }
				
			})
			.then(response => {
				this.patients = response.data
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
        	this.role = response.data;
        	if(response.data != "DERM" && response.data != "PHARM"){
        		window.location.href = '/login.html';
        	}
        	if(this.role == "DERM"){
    			axios
    			.get('/api/dermatologist/getLoggedUser',{
    				  headers: {
    					    'Authorization': "Bearer " + localStorage.getItem('access_token')
    				  }
    		     })
    		     .then(response => {
    		     	this.user = response.data
    		     })
    		}
    		else{
    			axios
    			.get('/api/pharmacist/getLoggedUser',{
    				  headers: {
    					    'Authorization': "Bearer " + localStorage.getItem('access_token')
    				  }
    		     })
    		     .then(response => {
    		     	this.user = response.data
    		     })
    		}
        })
        .catch(function() {
        	window.location.href = '/login.html';
	    })
		//get patients AXIOS
	}
})