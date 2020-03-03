package com.ownersite.rdr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable(false)
@Table(name = "Service")
public class Service extends BaseEntity{
	private String servicename;
	private String servicedec;

	@OneToMany(mappedBy = "service")
	private List<SubscriptionService> subscriptionServicRegistrations;

	public String getServicename(){
		return servicename;
	}

	public void setServicename(String servicename){
		this.servicename=servicename;
	}

	public String getServicedec(){
		return servicedec;
	}

	public void setServicedec(String servicedec){
		this.servicedec=servicedec;
	}

	public List<SubscriptionService> getSubscriptionServicRegistrations() {
		return subscriptionServicRegistrations;
	}

	public void setSubscriptionServicRegistrations(List<SubscriptionService> subscriptionServicRegistrations) {
		this.subscriptionServicRegistrations = subscriptionServicRegistrations;
	}

	@Override
	public String toString() {
		return "Service{" +
				"servicename='" + servicename + '\'' +
				", servicedec='" + servicedec + '\'' +
				'}';
	}
}