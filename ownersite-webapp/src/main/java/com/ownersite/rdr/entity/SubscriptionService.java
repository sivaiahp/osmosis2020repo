package com.ownersite.rdr.entity;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "subscriptionservice")
public class SubscriptionService extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "service_id")
    Service service;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public String toString() {
        return "SubscriptionService{" +
                "subscription=" + subscription +
                ", service=" + service +
                '}';
    }
}
