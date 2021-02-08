var app = new Vue({
	el: '#manageDerm',
	data: {
		orders: [],
		pharmacyId: "",
		admin: null,
		changePass: false,
		changeData: false,
		oldPass: "",
		newPass: "",
		newDerms: [],
		dermId: null,
		searchDermFirst: "",
		searchDermLast: "",
		orderedMed: []
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
		showMedicine(medicines){
			this.orderedMed = medicines;
			console.log(this.orderedMed)
			if(this.changePass == true){
				this.changePass = false;				
			}
			else
				this.changePass = true;
		},
		changeState(){
			if(this.changePass == true){
				this.changePass = false;
				
			}
			else
				this.changePass = true;
		},
		discardPassCh(){
			this.changePass = false;
		},
		
		finish(supplier, order){
			JSAlert.confirm("Are you sure that you want to approve this offer?").then(function(result) {
     		    if (!result)
     		        return;

					axios
					.get('/api/offer/approveOffer',
							{
								params:{
									offerId: supplier.offerId,
									errandId: order.id
								},
							headers: {
							    'Authorization': "Bearer " + localStorage.getItem('access_token')
							  }
						
					})
					.then(response => {
						if(response.data == true){
							axios
							.get('/api/offer/sendEmail',
									{
										params:{
											errandId: order.id
										},
									headers: {
									    'Authorization': "Bearer " + localStorage.getItem('access_token')
									  }
								
							})
							.then(response => {
								if(response.data){
									JSAlert.alert("You have successfully approved! Your suppliers will be informed!");
					                setTimeout(function () {
										window.location.href = '/pharmacyAdmin/managingOrders.html';
									}, 3000);
								}
							})
						}
					})
			
			});
		},
		formatDate(d){
			let date = new Date(d)
			return (date.getDate()-1) + "." + (date.getMonth() + 1) + "." + date.getFullYear() + ".";
		},
		calc(d){
			let date = new Date(d);
			let now = new Date();
			if(now.getTime() > date.getTime()){
				return false;
			}
			return true;
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
					.get('/api/errand/findAllErrands',
							{
							headers: {
							    'Authorization': "Bearer " + localStorage.getItem('access_token')
							  }
						
					})
					.then(response => {
						this.orders = response.data
	
					})
		     })	    
	}
})