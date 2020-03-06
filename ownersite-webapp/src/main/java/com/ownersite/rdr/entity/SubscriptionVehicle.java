package com.ownersite.rdr.entity;

import javax.persistence.*;

@Entity
@Cacheable(false)
@Table(name = "Subscriptionvehicle")
public class SubscriptionVehicle extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "subscription_id")
    Subscription subscription;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    Vehicle vehicle;

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle service) {
        this.vehicle = service;
    }

    @Override
    public String toString() {
        return "SubscriptionService{" +
                "subscription=" + subscription +
                ", service=" + vehicle +
                '}';
    }
}
