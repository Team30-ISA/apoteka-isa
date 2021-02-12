var app = new Vue({
  el: "#medicine",
  data: {
    medicineName: "",
    medicines: [],
    spec: null,
    showSpec: false,
    minGrade: 0,
    allMedicines: []
  },
  methods: {
    async search() {
      if (this.medicineName) {
        const { data } = await axios.get(
          `/api/medicine/allMedicine/${this.medicineName}`
        );
        this.allMedicines = data;
        this.medicines = data.filter((m) => m.pharmacy.grade >= this.minGrade);
      }
    },
    async getSpec(id) {
      const { data } = await axios.get(`/api/medicine/${id}`);
      this.showSpec = true;
      this.spec = data;
    },
    closeSpec() {
      this.showSpec = false;
      this.spec = null;
    }
  },
  watch: {
    minGrade() {
      if (this.allMedicines)
        this.medicines = this.allMedicines.filter(
          (m) => m.pharmacy.grade >= this.minGrade
        );
    }
  }
});
