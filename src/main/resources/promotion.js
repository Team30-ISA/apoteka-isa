var app = new Vue({
	el: '#promotion',
	data: {
		title: "",
		startOfPromotion: null,
		endOfPromotion: null,
		content: "",
	},
	methods: {
		submit:
			axios
	        .post('/api/newPromotion',
	
	            {
	                Title: this.title,
	                StartOfPromotion: this.startOfPromotion,
	                EndOfPromotion: this.endOfPromotion,
	                Content: this.content	
	            })
	        .then(response => {
	            JSAlert.alert("You have successfully published a new promotion!");
	        })
	        .catch(error => {
	            console.log(error)
	            if (error.response.status == 400) {
	                JSAlert.alert("There is already an account with this email adress");
	            } 
	            
	        })

	},
	created() {
		
	}
})