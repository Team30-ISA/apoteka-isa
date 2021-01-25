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
	            JSAlert.alert("You have successfully signed in!");
	            window.localStorage.setItem('access_token', response.data.accessToken);
	            
	        })
	        .catch(error => {
	            console.log(error)
	            if (error.response.status == 401) {
	                JSAlert.alert("Incorrect username or password!");
	            } 
	            
	        })
		}

	},
	created() {
		
	}
})