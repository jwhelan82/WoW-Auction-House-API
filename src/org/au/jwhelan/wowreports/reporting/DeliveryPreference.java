package org.au.jwhelan.wowreports.reporting;

/**
 * Stores a user's delivery preference.
 * 
 * May need to be refactored in future if other delivery
 * methods require it.
 * 
 * @author jwhelan
 *
 */
public class DeliveryPreference {
	
	private DeliveryType type;
	private String deliveryAddress;
	
	public DeliveryType getType() {
		return type;
	}
	public void setType(DeliveryType type) {
		this.type = type;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
}
