/**
 * 
 */
package com.ownersite.rdr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.MonthlySubscribers;
import com.ownersite.rdr.entity.Subscription;

/**
 * @author polamred
 *
 */
public interface SubscriptionJpaRepository extends JpaRepository<Subscription, Long>{

	@Query(value = "SELECT c from Subscription c where id =:id")
	Subscription findBySubscriptionId(@Param("id") long id);

	@Query(value = "SELECT s.subscriptionname AS subscriptionname, COALESCE(cs.subscribers, 0) as subscribers FROM subscription s LEFT JOIN \n"
			+ "(SELECT COUNT(*) AS subscribers, subscription_id FROM customer_subscription where customer_sub_startdate >= date_sub(now(), interval 6 month) GROUP BY subscription_id) AS cs ON s.id = cs.subscription_id", nativeQuery = true)
	List<MonthlySubscribers> countSubscriptions();
	
}
