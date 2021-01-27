var app = new Vue({
	el: '#promotion',
	data: {
		title: "",
		startOfPromotion: null,
		endOfPromotion: null,
		content: "",
		admin: null,
		pharmacyId: null
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
		submit: function (){
			console.log(new Date());
			if((this.startOfPromotion > this.endOfPromotion) || this.startOfPromotion < new Date() || this.endOfPromotion <= new Date){
				JSAlert.alert("Publishing is incorrect! Please check the data you have entered.");
			}else{
			axios
			.get('/api/pharmacyAdmin/getPharmacy',{
				  headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
				  }
		     })
		     .then(response => {
		     	this.pharmacyId = response.data
		     	axios
		        .post('/api/promotion',
		
		            {
		                title: this.title,
		                startOfPromotion: this.startOfPromotion,
		                endOfPromotion: this.endOfPromotion,
		                content: this.content,
		                pharmacyId: this.pharmacyId
		            })
		        .then(response => {
		        	JSAlert.stop();
	        		JSAlert.alert("You have successfully published a new promotion!");
		        	window.onload = setTimeout(function(){
		        	   locatio.reload();
		        	}, 5000);
		            
		        })
		        .catch(error => {
		            console.log(error)
		            if (error.response.status == 401 || error.response.status == 400) {
		                JSAlert.alert("Publishing is incorrect! Please check the data you have entered.");
		            } 
		            
		        })
		     })
			}
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
	}
})