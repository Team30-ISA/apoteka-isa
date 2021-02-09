var app = new Vue({
  el: "#promotions",
  data: {
    pharmacies: []
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
    async unsubscribe(id) {
      await axios.post("/api/promotion/unsubscribe", id, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token"),
          "Content-Type": "application/json"
        }
      });
      const { data } = await axios.get("/api/promotion", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.pharmacies = data;
    }
  },
  async created() {
    try {
      const { data } = await axios.get("/api/promotion", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });

      this.pharmacies = data;
    } catch (err) {
      console.log(err);
    }
  }
});
