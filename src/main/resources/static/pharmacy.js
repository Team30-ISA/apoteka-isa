var app = new Vue({
	el: '#pharmacy',
	data: {
		pharmacy: null,
		name: null,
		derms: [],
		pharms: []
	},
	methods: {

	},
	created() {
		let params = new URLSearchParams(location.search);
		this.name = params.get('name')
		axios
			.get('/api/pharmacy/findByName',
					{
						params:{
							name: this.name
						}
				
			})
			.then(response => {
				this.pharmacy = response.data
				axios
				.get('/api/pharmacy/findAllDermsInPharmacy',
						{
							params:{
								id: this.pharmacy.id
							}
					
				})
				.then(response => {
					this.derms = response.data
				})
				
				axios
				.get('/api/pharmacy/findAllPharmsInPharmacy',
						{
							params:{
								id: this.pharmacy.id
							}
					
				})
				.then(response => {
					this.pharms = response.data
				})
				
				
			
			})
		
		axios
			.get('/api/findAllPharms')
			.then(response => {
				this.pharms = response.data
				console.log(this.pharmacy)
			})
			
		
	}
})