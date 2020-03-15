package com.ownersite.rdr.dto;

/**
 * The DTO for id of the services associated with a subscription
 * 
 * @author basridha
 *
 */
public class SubscriptionServiceDTO {

	private String subscriptionId;
	private String serviceIds;

	public SubscriptionServiceDTO() {
		super();
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	};

}
