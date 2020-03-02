package com.ownersite.rdr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable(false)
@Table(name = "Subscription")
public class Subscription extends BaseEntity{
	private String subscriptionname;
	private String subscriptiondec;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "id", cascade = CascadeType.ALL)
	private List<Service> services;
	private long price;

	public String getSubscriptionname(){
		return subscriptionname;
	}

	public void setSubscriptionname(String subscriptionname){
		this.subscriptionname=subscriptionname;
	}

	public String getSubscriptiondec(){
		return subscriptiondec;
	}

	public void setSubscriptiondec(String subscriptiondec){
		this.subscriptiondec=subscriptiondec;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Subscription{" +
				"subscriptionname='" + subscriptionname + '\'' +
				", subscriptiondec='" + subscriptiondec + '\'' +
				", services=" + services +
				'}';
	}
}