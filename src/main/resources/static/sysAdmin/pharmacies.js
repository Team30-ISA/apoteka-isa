new Vue({
  el: "#pharmacies",
  data: {
    pharmacies: [],
    showAddPharmacyForm: false,
    name: "",
    address: ""
  },
  methods: {
    async submit() {
      try {
        await axios.post(
          "/api/pharmacy",
          { name: this.name, address: this.address },
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("access_token")
            }
          }
        );
        alert("Successfully added new pharmacy");
        this.showAddPharmacyForm = false;
      } catch (err) {
        alert("Pharmacy with given name or address already exist");
      }
    }
  },
  async created() {
    try {
      const { data } = await axios.get("/api/pharmacy/findAll", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.pharmacies = data;
    } catch (err) {
      console.log(err);
    }
  },
  watch: {
    showAddPharmacyForm() {
      this.name = "";
      this.address = "";
    }
  }
});
