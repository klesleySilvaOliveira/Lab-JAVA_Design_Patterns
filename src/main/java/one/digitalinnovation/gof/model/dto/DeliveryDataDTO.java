package one.digitalinnovation.gof.model.dto;

public class DeliveryDataDTO {

	private Double deliveryValue;
	private Integer days;
	
	public DeliveryDataDTO() {
		super();
	}
	
	public DeliveryDataDTO(Double deliveryValue, Integer days) {
		super();
		this.deliveryValue = deliveryValue;
		this.days = days;
	}

	public Double getDeliveryValue() {
		return deliveryValue;
	}

	public Integer getDays() {
		return days;
	}
	
}
