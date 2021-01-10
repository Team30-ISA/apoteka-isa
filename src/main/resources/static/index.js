var app = new Vue({
	el: '#pharmacies',
	data: {
		pharmacies: []
	},
	methods: {

	},
	created() {
		axios
			.get('/api/pharmacy')
			.then(response => {
				this.pharmacies = response.data
			})

	}
})