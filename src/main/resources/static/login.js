var app = new Vue({
	el: '#login',
	data: {
		username: "",
		password: "",
		info: ""
	},
	methods: {
		submit: function (){
			axios
	        .post('/auth/login',
	            {
	                username: this.username,
	                password: this.password
	            })
	        .then(response => {
	            window.localStorage.setItem('access_token', response.data.accessToken);
	            axios
	            .get('auth/getRole',{
	  			  headers: {
					    'Authorization': "Bearer " + localStorage.getItem('access_token')
	  			  }
	            })
	            .then(response => {
	            	if(response.data == "DERM"){
	            		window.location.href = 'dermatologist/dermatologistHome.html';
	            	}
	            	else if(response.data == "PHARM"){
	            		window.location.href = 'pharmacist/pharmacistHome.html';
	            	}
	            	else if(response.data == "PATIENT"){
	            		window.location.href = 'profile.html';
	            	}
	            })
	            
	        })
	        .catch(error => {
	            console.log(error)
	            if (error.response.status == 401) {
	                JSAlert.alert("Incorrect username or password!");
	            } 
	            
	        })
		}

	}
})