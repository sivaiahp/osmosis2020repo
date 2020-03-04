/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.Subscription;

/**
 * @author polamred
 *
 */
public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long>{

	@Query(value = "SELECT c from Subscription c where id =:id")
	Subscription findBySubscriptionId(@Param("id") long id);
	
}
