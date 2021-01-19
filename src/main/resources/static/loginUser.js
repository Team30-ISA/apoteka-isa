var app = new Vue({
	el: '#login',
	data: {
		email: "",
		password: "",
		passwordRepeat: ""
	},
	methods: {
		submit: function (){
			
			
			if (this.email.length !== 0 &&  this.password !== 0 &&  this.passwordRepeat !== 0) {
				axios
				.post('/auth/login',
		
					{
						email: this.email,
						password: this.password
					})
				.then(response => {
					localStorage.setItem('keyToken', response.data.accessToken)
					
					JSAlert.alert("You have successfully signed in!");
					window.location.href = "http://localhost:8083/index.html";
				})
				.catch(error => {
					console.log(error)
					if (error.response.status == 401) {
						JSAlert.alert("Incorrect username or password!");
					} 
					
				})
			}else {
				alert("You have to fill in the form!");
		}

	},
	created() {
		
	}
})