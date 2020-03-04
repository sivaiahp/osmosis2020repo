/**
 * 
 */
package com.ownersite.rdr.repository;

import com.ownersite.rdr.entity.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author polamred
 *
 */
public interface ServicesJpaRepository extends JpaRepository<Service, Long>{
	
	@Query(value = "SELECT c from Service c where id =:id")
	Service findByServiceId(@Param("id") long id);
	
	@Query(value = "SELECT c from Service c, SubscriptionService d where d.subscription.id =:subscriptionsId and c.id = d.service.id")
	List<Service> getAllServicesMappedToSubscriptionId(@Param("subscriptionsId") long subscriptionsId);
}
