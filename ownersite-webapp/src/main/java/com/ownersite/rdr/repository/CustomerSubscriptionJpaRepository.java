/**
 * 
 */
package com.ownersite.rdr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.ownersite.rdr.entity.CustomerSubscription;

/**
 * @author polamred
 *
 */
public interface CustomerSubscriptionJpaRepository extends JpaRepository<CustomerSubscription, Long>{

	@Query(value = "SELECT c from CustomerSubscription c where c.subscription.id =:subscriptionId")
	List<CustomerSubscription> findBySubscriptionId(@Param("subscriptionId") long subscriptionId);

	
	@Query(value = "SELECT c from CustomerSubscription c where customerId =:customerId")
    List<CustomerSubscription> findByCustomerId(@Param("customerId") long customerId);
    
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM CustomerSubscription c where customerId =:customerId")
	void cancelCustomerSubscriptionByCustomerId(@Param("customerId") long customerId);

    List<CustomerSubscription> findBySubscriptionIdAndCustomerId(Long subscriptionId, Long customerId);

    List<CustomerSubscription> findBySubscriptionIdAndCustomerIdAndVin(String subscriptionId, String customerId, String vin);
}
