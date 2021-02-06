var app = new Vue({
  el: "#login",
  data: {
    email: "",
    password: "",
    info: "",
    validationErrors: {},
    isFormValid: false
  },
  methods: {
    submit() {
      axios
        .post("/auth/login", {
          email: this.email,
          password: this.password
        })
        .then((response) => {
          window.localStorage.setItem(
            "access_token",
            response.data.accessToken
          );
          window.localStorage.setItem(
            "user",
            JSON.stringify(response.data.user)
          );
          axios
            .get("auth/getRole", {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("access_token")
              }
            })
            .then((response) => {
              if (response.data == "DERM" && this.email !== this.password) {
                window.location.href = "dermatologist/dermatologistHome.html";
              } else if (
                response.data == "PHARM" &&
                this.email !== this.password
              ) {
                window.location.href = "pharmacist/pharmacistHome.html";
              } else if (
                response.data == "ADMIN" &&
                this.email !== this.password
              ) {
                window.location.href = "pharmacyAdmin/pharmacyAdminHome.html";
              } else if (response.data == "PATIENT") {
                window.location.href = "profile.html";
              } else {
                window.location.href = "changePassword.html";
              }
            });
        })
        .catch((error) => {
          console.log(error);
          if (error.response.status == 401) {
            JSAlert.alert("Incorrect email or password!");
          }
        });
    },
    validateForm() {
      this.validationErrors = {};
      this.isFormValid = true;
      if (!this.email || !this.password) this.isFormValid = false;
      this.validatePassword();
      this.validateEmail();
    },
    validateEmail() {
      if (this.email && !this.email.match(REGEX.EMAIL_REGEX)) {
        this.validationErrors = {
          email: "Email must have valid email format."
        };
        this.isFormValid = false;
      }
      if (this.email.match(REGEX.STARTS_WITH_SPACE)) {
        this.validationErrors = {
          email: "Email can't start with space."
        };
        this.isFormValid = false;
      }
    },
    validatePassword() {
      if (this.password.match(REGEX.STARTS_WITH_SPACE)) {
        this.validationErrors = {
          ...this.validationErrors,
          password: "Password can't start with space."
        };
        this.isFormValid = false;
      }
      if (this.password && this.password.length < 6) {
        this.validationErrors = {
          ...this.validationErrors,
          password: "Password min length is 6 characters."
        };
        this.isFormValid = false;
      }
    }
  }
});
