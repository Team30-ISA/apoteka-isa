var app = new Vue({
  el: "#login",
  data: {
    username: "",
    password: "",
    info: ""
  },
  methods: {
    submit: function () {
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
    }
  }
});
