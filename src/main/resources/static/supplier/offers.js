var app = new Vue({
  el: "#offers",
  data: {
    offers: []
  },
  methods: {},
  async created() {
    await axios.get("/api/offer");
  }
});
