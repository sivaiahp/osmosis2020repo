/**
 * 
 */
package com.ownersite.rdr.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.SubscriptionService;

/**
 * @author polamred
 *
 */
public interface SubscriptionServiceJpaRepository extends JpaRepository<SubscriptionService, Long>{

	@Query(value = "SELECT c from SubscriptionService c where subscription.id =:id")
	List<SubscriptionService> findBySubscriptionId(@Param("id") long id);
	
	@Query(value = "SELECT c from SubscriptionService c where service.id =:id")
	List<SubscriptionService> findByServiceId(@Param("id") long id);
	
	@Transactional
	@Modifying
	@Query(value = "DELETE FROM SubscriptionService c WHERE c.subscription.id =:subscriptionId")
	void deleteBySubscriptionId(@Param("subscriptionId") long subscriptionId);
}
