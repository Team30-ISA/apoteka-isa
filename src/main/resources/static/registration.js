var app = new Vue({
    el: '#register',
    data: function () {
        return {
            email: '',
            password: '',
			confirmPassword: '',
			firstName: '',
			lastName: '',
			address: '',
			city: '',
			country: '',
			phoneNumber: ''
        }
    },
    template: ` 
	<div class="container">
            <h5 class=" text-center  mb-0 text-uppercase" style="margin-top: 2rem;">Registration</h5>
    
            <div class="row section-design">
                <div class="col-lg-8 mx-auto">
                    <br/>
                   
                    <form id="contactForm" name="sentMessage" novalidate="novalidate">
    
                         <div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="email" placeholder="Email" class="form-control" id="name" type="text"  />
                            </div>
                        </div>

                        <div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="password" placeholder="Password" class="form-control" id="password" type="password"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="confirmPassword" placeholder="Confirm password" class="form-control" id="confirm_password" type="password"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="firstName" placeholder="First name" class="form-control" id="first_name" type="text"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="lastName" placeholder="Last name" class="form-control" id="last_name" type="text"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="address" placeholder="Address" class="form-control" id="address" type="text"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="city" placeholder="City" class="form-control" id="city" type="text"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="country" placeholder="Country" class="form-control" id="country" type="text"  />
                            </div>
                        </div>
						
						<div class="control-group">
                            <div class="form-group controls mb-0 pb-2" style=" color: #6c757d;opacity: 1;">
                                <input v-model="phoneNumber" placeholder="Phone number" class="form-control" id="phone_number" type="text"  />
                            </div>
                        </div>
                        
						<div  class="form-group">
                              <button v-on:click="registration" style="background: #1977cc;margin-top: 15px;margin-left: 40%; width: 20%;" class="btn btn-primary btn-xl" id="sendMessageButton" type="button">Register</button>
                        </div>
                    
                       
                       </form>
                <div>
                </div>
                </div>
            </div>
        </div>
`,
    created() {
        var role = localStorage.getItem('keyRole');

        if (role == 1) {
            window.location.href = "/public/index.html";
        } else if (role == 0) {
            window.location.href = "/public/index.html";
        }

        
    },
    methods: {
		
		if (password !== confirmPassword){
                alert("Password did not match!")
                return
            }  
			
		if(firstName === "" || lastName === "" || email === "" || password === "" || confirmPassword === "" || address === "" || city === "" || country === "" || phoneNumber===""){
				alert("Fill all the fields!")
				return
			}
		
        registration: function () {
            axios
                .post("/api/User/registration", {
                    Email: this.email,
                    Password: this.password,
					FirstName: this.firstName,
					LastName: this.lastName,
					Address: this.address,
					City: this.city,
					Country: this.country,
					PhoneNumber: this.phoneNumber
                }).then((response) => {
                    if (response.status === 204) {
                        alert("This email  already exists!");
                        return;
					}
					if(response.status === 200){
                        alert("Registration successful!");
						window.location.href = "http://localhost:8081/login.html";
					}
                }).catch(error => {
                    alert("Neuspesno" + error)
					});
        }
    }
});