new Vue({
  el: "#manageMedications",
  data: {
    name: "",
    type: "",
    form: "",
    contraindications: "",
    composition: "",
    recommendedIntakePerDay: "",
    manufacturer: "",
    forms: [],
    types: [],
    medicines: []
  },
  methods: {
    createMedicine() {}
  },
  async created() {
    console.log("created");
    try {
      let resp = await axios.get("/api/medicine/types", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.types = resp.data;
      resp = await axios.get("/api/medicine/forms", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.forms = resp.data;
      resp = await axios.get("/api/medicine/findAllMedicine", {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("access_token")
        }
      });
      this.medicines = resp.data;
    } catch (err) {
      console.log(err);
    }
  }
});
