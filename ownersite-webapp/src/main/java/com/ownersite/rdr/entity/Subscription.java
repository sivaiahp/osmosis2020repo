package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Cacheable(false)
@Table(name = "Subscription")
public class Subscription extends BaseEntity{
	private String subscriptionname;
	private String subscriptiondec;
	private List<Services> services;
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

	public List<Services> getServices() {
		return services;
	}

	public void setServices(List<Services> services) {
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