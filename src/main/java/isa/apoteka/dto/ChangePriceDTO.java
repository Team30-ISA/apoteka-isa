package isa.apoteka.dto;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;

public class ChangePriceDTO {
	private Long medicineId;
	private int newPrice;
	@FutureOrPresent(message="Start of price validity can only be a future or present date.")
	private Date startOfPrice;
	@Future(message="Start of price validity can only be a future date.")
	private Date endOfPrice;
	public ChangePriceDTO() {
		super();
	}
	public ChangePriceDTO(Long medicineId, int newPrice, Date startOfPrice, Date endOfPrice) {
		super();
		this.medicineId = medicineId;
		this.newPrice = newPrice;
		this.startOfPrice = startOfPrice;
		this.endOfPrice = endOfPrice;
	}
	public Long getMedicineId() {
		return medicineId;
	}
	public void setMedicineId(Long medicineId) {
		this.medicineId = medicineId;
	}
	public int getNewPrice() {
		return newPrice;
	}
	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
	}
	public Date getStartOfPrice() {
		return startOfPrice;
	}
	public void setStartOfPrice(Date startOfPrice) {
		this.startOfPrice = startOfPrice;
	}
	public Date getEndOfPrice() {
		return endOfPrice;
	}
	public void setEndOfPrice(Date endOfPrice) {
		this.endOfPrice = endOfPrice;
	}
	
	
}
