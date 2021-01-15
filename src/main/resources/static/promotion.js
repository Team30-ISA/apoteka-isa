var app = new Vue({
	el: '#promotion',
	data: {
		title: "",
		startOfPromotion: null,
		endOfPromotion: null,
		content: "",
	},
	methods: {
		submit: function (){
			axios
	        .post('/api/promotion',
	
	            {
	                title: this.title,
	                startOfPromotion: this.startOfPromotion,
	                endOfPromotion: this.endOfPromotion,
	                content: this.content	
	            })
	        .then(response => {
	            JSAlert.alert("You have successfully published a new promotion!");
	        })
	        .catch(error => {
	            console.log(error)
	            if (error.response.status == 401) {
	                JSAlert.alert("Publishing is incorrect!");
	            } 
	            
	        })
		}

	},
	created() {
		
	}
})