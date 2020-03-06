package com.ownersite.rdr.dto;

public class SubscriptionVehicleDTO {

	private String subscriptionId;
	private String vehicleIds;

	public SubscriptionVehicleDTO() {
		super();
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getVehicleIds() {
		return vehicleIds;
	}

	public void setVehicleIds(String vehicleIds) {
		this.vehicleIds = vehicleIds;
	};

}
