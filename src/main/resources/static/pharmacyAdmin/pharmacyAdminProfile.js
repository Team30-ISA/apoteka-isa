var app = new Vue({
	el: '#adminProfile',
	data: {
		admin: null,
		changePass: false,
		changeData: false,
		oldPass: "",
		newPass: "",
		repeatPass: "",
		info: null
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
				//axios za izmenu podataka
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