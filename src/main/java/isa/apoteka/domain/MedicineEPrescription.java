package isa.apoteka.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="MedicineEprescription")
public class MedicineEPrescription {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id", unique = true, nullable = false)
		private Long id;

	    @Column(name = "nameMedicine")
	    private String name;

	    @Column(name = "Code")
	    private Long code;

	    @Column(name = "Quantity")
	    private Integer quantity;

	    @JsonBackReference(value="EPrescriptionPatients")
	    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
	    @JoinColumn(name = "ePrescriptionMedication", referencedColumnName = "id", unique = false)
	    private EPrescription ePrescription;

		public MedicineEPrescription() {
		}
		
		public MedicineEPrescription(Long id, String name, Long code, Integer quantity, EPrescription ePrescription) {
			super();
			this.id = id;
			this.name = name;
			this.code = code;
			this.quantity = quantity;
			this.ePrescription = ePrescription;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Long getCode() {
			return code;
		}

		public void setCode(Long code) {
			this.code = code;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public void setQuantity(Integer quantity) {
			this.quantity = quantity;
		}

		public EPrescription getePrescription() {
			return ePrescription;
		}

		public void setePrescription(EPrescription ePrescription) {
			this.ePrescription = ePrescription;
		}
	    
}
