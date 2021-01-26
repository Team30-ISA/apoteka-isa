var app = new Vue({
	el: '#profile',
	data: {
		patients: null
	},
	methods: {

	},
	created() {
		axios
			.get('/api/patient/10',
					{
				
			}).then(response => {
					this.patients = response.data
			})
		
	}
})