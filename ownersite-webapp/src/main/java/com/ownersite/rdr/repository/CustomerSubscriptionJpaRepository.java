/**
 * 
 */
package com.ownersite.rdr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ownersite.rdr.entity.Customer;
import com.ownersite.rdr.entity.CustomerSubscription;

/**
 * @author polamred
 *
 */
public interface CustomerSubscriptionJpaRepository extends JpaRepository<CustomerSubscription, Long>{

	@Query(value = "SELECT c from CustomerSubscription c where c.subscription.id =:subscriptionId")
	List<CustomerSubscription> findBySubscriptionId(@Param("subscriptionId") long subscriptionId);
    List<CustomerSubscription> findByCustomer(Customer customer);

    List<CustomerSubscription> findBySubscriptionIdAndCustomerId(String subscriptionId, String customerId);

    List<CustomerSubscription> findBySubscriptionIdAndCustomerIdAndVin(String subscriptionId, String customerId, String vin);
}
