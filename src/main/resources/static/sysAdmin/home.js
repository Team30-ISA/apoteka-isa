new Vue({
  el: "#home",
  data: {
    pharmacies: []
  },
  methods: {},
  created() {
    axios.get("/api/pharmacy/findAll").then((response) => {
      this.pharmacies = response.data;
    });
  }
});
