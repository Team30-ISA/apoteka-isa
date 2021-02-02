var app = new Vue({
	el: '#page',
	data: {
        //currentStep: "START",
		currentStep: "PRESCRIPT",
        examination: null,
        monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
        report: "",
        medicines: null,
        buff: null,
        drugInfo: false,
        selectedDrug: null,
        sname: "",
        showSubs: false,
        subDrug: null,
        therapies: [],
        showTherapies: false
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
        },
        selectDrug(drug){
        	this.selectedDrug = drug;
        	this.drugInfo = true;
        },
        prescribeDrug(d){
        	this.selectedDrug = d;
        	axios
    		.get('/api/medicine/isPatientAllergic',{
    			headers: {
    			 'Authorization': "Bearer " + localStorage.getItem('access_token')
    			},
    			params:{
    				medicineId: d.id,
    				counselingId: this.examination.id
    			}
    	     })
    	     .then(response => {
    	    	 let th = this
    	     	if(response.data == true){
    	     		JSAlert.confirm("Pacijent je alergican na ovaj lek, prikazi zamene?").then(function(result) {
    	     		    if (!result)
    	     		        return;
    	     		   th.subDrug = d
    	     		   th.getSubstitutes(d);
    	     		});
    	     		
    	     	}
    	     	else if(response.data == false){
    	     		this.isMedicineAvailable();
    	     	} 	
    	     	else
    	     		JSAlert.alert("Greska!");
    	     })
        },
        getSubstitutes(d){
        	this.selectedDrug = d;
        	axios
    		.get('/api/medicine/getSubstitutesOfMedicine',{
    			headers: {
    			 'Authorization': "Bearer " + localStorage.getItem('access_token')
    			},
    			params:{
    				id: d.id,
    			}
    	     })
    	     .then(response => {
    	    	 if(this.showSubs == false)
    	    		 this.buff = this.medicines;
    	    	 this.medicines = response.data;
    	    	 this.showSubs = true;
    	     })
        },
        search(){
        	axios
    		.get('/api/medicine/searchMedicinesByName',{
    			headers: {
    			 'Authorization': "Bearer " + localStorage.getItem('access_token')
    			},
    			params:{
    				name: this.sname,
    			}
    	     })
    	     .then(response => {
    	     	this.medicines = response.data
    	     })
        },
        goBack(){
        	this.showSubs = false;
    	    this.medicines = this.buff;
        },
        isMedicineAvailable(){
        	axios
    		.get('/api/medicine/isMedicineAvailableInPharmacy',{
    			headers: {
    			 'Authorization': "Bearer " + localStorage.getItem('access_token')
    			},
    			params:{
    				medicineId: this.selectedDrug.id,
    				counselingId: this.examination.id 
    			}
    	     })
    	     .then(response => {
    	    	 let th = this;
    	    	 JSAlert.prompt("Unesite trajanje terapije u danima?").then(function(result) {
 	     		    if (!result)
 	     		        return;
 	     		  axios
 	     		  .get('/api/therapy/save',{
 	     			  headers: {
 	     				  'Authorization': "Bearer " + localStorage.getItem('access_token')
 	     			  },
 	     			  params:{
 	     				duration: result,
		    			medicineId: th.selectedDrug.id,
		    			counselingId: th.examination.id 
 	     			  }
		    	   })
		    	   .then(response => {
		    	     	th.therapies.push(response.data)
		    	     	console.log(th.therapies)
		    	     	JSAlert.alert("Terapija uspesno upisana!")
		    	   })
 	     		});
    	     })
        },
        changeShowTherapiesStatus(){
        	if(this.showTherapies == false)
        		this.showTherapies = true;
        	else
        		this.showTherapies = false;
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