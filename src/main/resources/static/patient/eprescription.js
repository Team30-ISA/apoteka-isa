var app = new Vue({
	el: '#eprescription',
	data: {
	   pharmacyList : [],
       formData: null,
       urlImage: [],
       lista : [],
       file: '',
       medications : [],
       pharmacyName : "",
       pharmacyCountry : "",
       pharmacyTown : "",
       pharmacyListFilter : [],
       dermatologistAppointment: null,
       myEPrescriptions: [],
       isAuthorized : false,
       eReceiptCode : "",
	   patient: null,
	   selectedFiles: [],  
	   selectedFile : null
  	
	},
	methods: {
		logout() {
      axios
        .post("/auth/logout", null, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("access_token")
          }
        })
        .then(function () {
          localStorage.clear();
          window.location.href = "/login.html";
        });
    },
	
		submitFile(){
            
            let formData = new FormData()
            formData.append('file', this.file);
            this.pharmacyList = [];
            this.pharmacyListFilter=[];

            axios.
				post( '/api/eprescription/uploadFile', formData, {
					headers: {
						'Content-Type': 'multipart/form-data',
						 Authorization: "Bearer " + localStorage.getItem("access_token")
					}
				}). then(response => {
					this.pharmacyList = response.data.pharmacies;
                    this.pharmacyListFilter = response.data.pharmacies;
                    this.medications = response.data.medicationsInQRcode;
					for (var key in this.medications ) {
					console.log(key, this.medications[key].medicationName);
					console.log(key, this.medications[key].medicationCode);
					console.log(key, this.medications[key].quantity);	
					}
                    this.eReceiptCode = response.data.code;
					console.log(this.eReceiptCode)
                    if(this.pharmacyList.length===0) {
                        JSAlert.alert("There is no pharmacy that has all mediciations.")
                    }    
                }).catch(error => {
	            if (error.response.status == 401 || error.response.status == 400) {
	                JSAlert.alert("You must select a QR code picture first!");
	            } else if(error.response.status == 500){	 
					JSAlert.alert("This ePrescription is already used!");
				}
                });     
		},
		
		choosePharmacy($event,pharmacy) { 
        console.log(pharmacy)
        const request = {
			pharmacyId : pharmacy,
            medications : this.medications,
            code : this.eReceiptCode
        }
        axios.post( '/api/eprescription/choosePharmacy', request,{
                          headers: {
                              'Authorization': "Bearer " + localStorage.getItem('access_token')
                          }
                        }). then(function() {
                            JSAlert.alert("Medicines are successfully taken from choosen pharmacy!");
							/*setTimeout(function () {
								window.location.href = "/patient/eprescription.html";
								}, 2000);*/
                        }).catch(res => {
                            JSAlert.alert("This eReceipt is already used!");
                            console.log(res);
                        });     
      },
	handleFileUpload(){
		this.file = event.target.files[0];
		console.log(this.file)
	},		
	gradeDecreasing: function() {
        return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = -1;
                    if(p1.grade < p2.grade) return -1 * modifier; if(p1.grade > p2.grade) return 1 * modifier;
                    return 0;
                });
    },
    gradeInreasing : function( ) {
      return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = 1;
                    if(p1.grade < p2.grade) return -1 * modifier; if(p1.grade > p2.grade) return 1 * modifier;
                    return 0;
                });
     },
     priceDecreasing : function() {
             return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = -1;
                    if(p1.price < p2.price) return -1 * modifier; if(p1.price > p2.price) return 1 * modifier;
                    return 0;
                });
     },
     priceInreasing : function() {
         return this.pharmacyListFilter.sort((p1,p2) => {
                    let modifier = 1;
                    if(p1.price < p2.price) return -1 * modifier; if(p1.price > p2.price) return 1 * modifier;
                    return 0;
                });
     },
     nameInreasing : function() {
        this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.pharmacyName > b.pharmacyName) ? 1 : -1;
            });
     },
     nameDecreasing : function() {
         this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.pharmacyName < b.pharmacyName) ? 1 : -1;
            });
     },
     streetInreasing : function() {
        this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.street > b.street) ? 1 : -1;
            });
     },
     streetDecreasing : function() {
         this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.street < b.street) ? 1 : -1;
            });  
     },
     townInreasing : function() {
         this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.city > b.city) ? 1 : -1;
            });
     },
     townDecreasing : function() {
        this.pharmacyListFilter =  this.pharmacyListFilter.slice().sort(function(a, b){
                return (a.city < b.city) ? 1 : -1;
            });
     }		
	},
	created() {
		
		axios
		.get('/api/patient/getLoggedUser',{
			  headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
	     })
	     .then(response => {
	     	this.patient = response.data;
			var id = this.patient.id;
			console.log(this.patient.id)
			axios
				.get('/api/eprescription/all/' + this.patient.id,{
					headers: {
						'Authorization': "Bearer " + localStorage.getItem('access_token')
					}
				})
			.then(response => {
                this.myEPrescriptions = response.data;
                console.log(response.data);
				for (var key in this.myEPrescriptions) {
					console.log(key, this.myEPrescriptions[key].pharmacyName);
				}
              
			}).catch(res => {
					JSAlert.alert(res.response.data);
                });
         
         }).catch(res => {
		   JSAlert.alert("Please log in first!");
           });
		
		axios
			.get('/api/pharmacy/findAll',{
				headers: {
				    'Authorization': "Bearer " + localStorage.getItem('access_token')
			  }
			})
			.then(response => {
				this.pharmacies = response.data
			})

	}
})