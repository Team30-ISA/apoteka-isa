package isa.apoteka.dto;

public class EarningDTO {
	private Float earningE;
	private Float earningC;
	private Float earningM;
	public EarningDTO(Float earningE, Float earningC, Float earningM) {
		super();
		this.earningE = earningE;
		this.earningC = earningC;
		this.earningM = earningM;
	}
	public Float getEarningE() {
		return earningE;
	}
	public void setEarningE(Float earningE) {
		this.earningE = earningE;
	}
	public Float getEarningC() {
		return earningC;
	}
	public void setEarningC(Float earningC) {
		this.earningC = earningC;
	}
	public Float getEarningM() {
		return earningM;
	}
	public void setEarningM(Float earningM) {
		this.earningM = earningM;
	}
	public EarningDTO() {
		super();
	}
	
	
}
