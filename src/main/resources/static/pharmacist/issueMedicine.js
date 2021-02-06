var app = new Vue({
	el: '#pharmacist',
	data: {
		pharm: null,
		uid: ""
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
		redirect(p){
			window.location.href = p;
		},
		search(){
			axios
            .get('/api/medicineReservation/findReservation',{
    			  headers: {
    			    'Authorization': "Bearer " + localStorage.getItem('access_token')
    			  },
    			  params: {
    				  uid: this.uid
    			  }
            })
            .then(response => {
            	
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
        	if(response.data != "PHARM"){
        		window.location.href = '/login.html';
        	}
        })
        .catch(function() {
        	window.location.href = '/login.html';
	    })
		axios
		.get('/api/pharmacist/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.pharm = response.data
	     })
	}
})