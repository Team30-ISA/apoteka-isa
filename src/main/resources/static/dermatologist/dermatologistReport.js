var app = new Vue({
	el: '#page',
	data: {
        currentStep: "START",
		derm: null,
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
        showTherapies: false,
        canStart: false,
        currentExam: null,
        waitingTime: ""
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
    	    	 if(response.data == false){
    	    		 JSAlert.confirm("Lek nije dostupan u apoteci, prikazi zamene?").then(function(av) {
     	     		    if (!av)
     	     		        return;
     	     		   th.subDrug = th.selectedDrug
     	     		   th.getSubstitutes(th.selectedDrug);
     	     		   return;
     	     		});
    	    	 }
    	    	 else{
    	    		 JSAlert.prompt("Unesite trajanje terapije u danima?").then(function(result) {
	 	     		    if (!result)
	 	     		        return;
	 	     		  if(isNaN(parseInt(result))){
	 	     			JSAlert.alert("Neispravan unos!");
	 	     			return;
	 	     		  }
		 	     	  axios
		 	     	  .post('/api/therapy/save',
		 	     			  {
		 	     			  	duration: parseInt(result),
		 	     			  	medicineId: th.selectedDrug.id,
				    			counselingId: th.examination.id 
			     			  },{
			     				 headers: {
			     					 'Authorization': "Bearer " + localStorage.getItem('access_token')
			 	     			 }
				    	   })
					    .then(response => {
					    	     	th.therapies.push(response.data)
					    })
	 	     		});
    	    	 }
    	     })
        },
        changeShowTherapiesStatus(){
        	if(this.showTherapies == false)
        		this.showTherapies = true;
        	else
        		this.showTherapies = false;
        },
        calc(){
        	let now = new Date();
        	let endOfExam = new Date(this.examination.startDate.getTime() + 60000*this.examination.duration);
        	if(this.examination.startDate <= now && now <= endOfExam)
        		this.canStart = true;
        	else{
        		this.dateDistance(now, this.examination.startDate)
        		setTimeout(this.calc, 60000);
        	}
        	
        },
        dateDistance(date1, date2){
        	let ret = ""
        	let diffTime = Math.abs(date2 - date1);
        	let diffDays = Math.floor(diffTime / (1000 * 60 * 60 * 24));
        	let diffHrs = Math.floor((diffTime % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
        	let diffMins = Math.floor((diffTime % (1000 * 60 * 60)) / (1000 * 60));
        	if(diffDays == 1)
        		ret = diffDays + " day ";
        	else if(diffDays > 1)
            	ret = diffDays + " days ";
        	
        	if(diffDays == 0 && diffHrs == 0){}
        	else if(diffHrs <= 1)
        		ret += diffHrs + " hour ";
        	else if(diffDays > 1)
            	ret += diffHrs + " hours ";
        	if(diffDays == 0 && diffHrs == 0 && diffMins < 1)
        		ret = "less than one minute";
        	else
        		ret += diffMins + " min ";
        	this.waitingTime = ret;
        }
	},
	created() {
		axios
        .get('/auth/getRole',{
			  headers: {
			    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
        })
        .then(response => {
        	if(response.data != "DERM"){
        		window.location.href = '/login.html';
        	}
        })
        .catch(function() {
        	window.location.href = '/login.html';
	    })
		axios
		.get('/api/dermatologist/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.derm = response.data
	     })
	     axios
		.get('/api/counseling/getNearestCounseling',{
			headers: {
			 'Authorization': "Bearer " + localStorage.getItem('access_token')
			},
			params:{
				start: (new Date).getTime(),
			}
	     })
	     .then(response => {
	    	 this.examination = response.data
	 	     this.examination.startDate = new Date(this.examination.startDate)
	    	 this.calc();
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