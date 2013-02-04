package org.au.jwhelan.wowreports.reporting;

import java.util.Set;

/**
 * Encapsulate a user plus the reports they subscribe to 
 * and their delivery choices.
 * 
 * @author James Whelan
 *
 */
public class User {
	String username;
	Set<DeliveryPreference> deliveryPreferences;
	Set<ReportType> subscribedReports;
	
	public User(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Set<DeliveryPreference> getDeliveryPreferences() {
		return deliveryPreferences;
	}
	public void setDeliveryPreferences(Set<DeliveryPreference> deliveryPreferences) {
		this.deliveryPreferences = deliveryPreferences;
	}
	public Set<ReportType> getSubscribedReports() {
		return subscribedReports;
	}
	public void setSubscribedReports(Set<ReportType> subscribedReports) {
		this.subscribedReports = subscribedReports;
	}
	
}
