var app = new Vue({
	el: '#profile',
	data: {
		patients: null,
		oldpass: "",
		newpass: "",
		name: "",
		surname: "",
	},
	methods: {
		changepass() {
		axios.get('/api/patient/updatePassword',{
		headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  },
		params:{
			oldpass: this.oldpass,
			newpass: this.newpass,
			id: this.patients.id,
			}
		}).then(response => {
					alert('Lozinka je uspesno promenjena.')
			})
		},
		changedata(){
		axios.get('/api/patient/updatePatient',{
		headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  },
		params:{
			name: this.name,
			surname: this.surname,
			id: this.patients.id,
			}
		}).then(response => {
					alert('Novo ime: ' + this.name + '\nNovo prezime: ' + this.surname)
			})
		}
	},
	created() {
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     }).then(response => {
					this.patients = response.data
					this.name = this.patients.firstName
					this.surname = this.patients.lastName
			})
		
	}
})