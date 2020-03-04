package com.ownersite.rdr.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Cacheable(false)
@Table(name = "Subscription")
public class Subscription extends BaseEntity {
	private String subscriptionname;
	private String subscriptiondec;

	@OneToMany(mappedBy = "subscription", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<SubscriptionService> subscriptionServicRegistrations;

	private long price;

	public String getSubscriptionname() {
		return subscriptionname;
	}

	public void setSubscriptionname(String subscriptionname) {
		this.subscriptionname = subscriptionname;
	}

	public String getSubscriptiondec() {
		return subscriptiondec;
	}

	public void setSubscriptiondec(String subscriptiondec) {
		this.subscriptiondec = subscriptiondec;
	}

	public List<SubscriptionService> getSubscriptionServicRegistrations() {
		return subscriptionServicRegistrations;
	}

	public void setSubscriptionServicRegistrations(List<SubscriptionService> subscriptionServicRegistrations) {
		this.subscriptionServicRegistrations = subscriptionServicRegistrations;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Subscription{" + "subscriptionname='" + subscriptionname + '\'' + ", subscriptiondec='"
				+ subscriptiondec + '\'' + '}';
	}
}