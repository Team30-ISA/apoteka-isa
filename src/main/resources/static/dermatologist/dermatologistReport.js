var app = new Vue({
	el: '#page',
	data: {
        currentStep: "START",
        examination: null,
        monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        report: "",
        medicines: null
	},
	methods: {
        formatDate(date){
        	return this.monthNames[date.getMonth()] + " " + date.getDate() + " " + date.getFullYear() + ", " + date.toLocaleTimeString('it-IT');
        },
        goLeft(){
        	if(this.currentStep == "REPORT")
        		this.currentStep = "START";
        	else if(this.currentStep == "PRESCRIPT")
        		this.currentStep = "REPORT";
        	else if(this.currentStep == "SCHEDULE")
        		this.currentStep = "PRESCRIPT";
        },
        goRight(){
        	if(this.currentStep == "START")
        		this.currentStep = "REPORT";
        	else if(this.currentStep == "REPORT")
        		this.currentStep = "PRESCRIPT";
        	else if(this.currentStep == "PRESCRIPT")
        		this.currentStep = "SCHEDULE";
        }
	},
	created() {
		axios
		.get('/api/counseling/getById',{
			headers: {
			 'Authorization': "Bearer " + localStorage.getItem('access_token')
			},
			params:{
				id: 1,
			}
	     })
	     .then(response => {
	     	this.examination = response.data
	     	this.examination.startDate = new Date(this.examination.startDate)
	     })
	     axios
		.get('/api/medicine/getAll',{
			headers: {
			 'Authorization': "Bearer " + localStorage.getItem('access_token')
			}
	     })
	     .then(response => {
	     	this.medicines = response.data
	     })
	}
})