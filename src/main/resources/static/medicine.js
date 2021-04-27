var app = new Vue({
  el: "#medicine",
  data: {
    medicineName: "",
    medicines: [],
    spec: null,
    showSpec: false,
    minGrade: 0,
    allMedicines: [],
	type: ''
  },
  methods: {
    async search() {
      if (this.medicineName) {
        const { data } = await axios.get(
          `/api/medicine/allMedicine/${this.medicineName}`
        );
		if(data.length === 0)
		{
			JSAlert.alert("Medicine doesn't exist!")
			this.medicineName = ""
			return
		}
        this.allMedicines = data;
		for (var key in this.allMedicines) {
			  console.log(key, this.allMedicines[key].medicine.name);
			  console.log(key, this.allMedicines[key].medicine.form.name);
			  console.log(key, this.allMedicines[key].medicine.type.name);
			  
		}
		
        this.medicines = data.filter((m) => m.pharmacy.grade >= this.minGrade);
		this.medicines = data.filter((m) => m.medicine.type.name >= this.type);
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
    },
	
	type() {
	  if (this.allMedicines)
        this.medicines = this.allMedicines.filter(
          (m) => m.medicine.type.name >= this.type
        );
    }
  }
});
