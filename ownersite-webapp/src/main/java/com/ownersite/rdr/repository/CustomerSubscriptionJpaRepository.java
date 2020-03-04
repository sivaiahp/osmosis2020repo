/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.CustomerSubscription;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerSubscriptionJpaRepository extends JpaRepository<CustomerSubscription, Long>{

    List<CustomerSubscription> findByCustomer();
}
