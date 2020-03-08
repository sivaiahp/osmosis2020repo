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
import com.ownersite.rdr.entity.MonthlySubscribers;

/**
 * @author polamred
 *
 */
public interface CustomerSubscriptionJpaRepository extends JpaRepository<CustomerSubscription, Long> {

	@Query(value = "SELECT c from CustomerSubscription c where c.subscription.id =:subscriptionId")
	List<CustomerSubscription> findBySubscriptionId(@Param("subscriptionId") long subscriptionId);

	@Query(value = "SELECT c from CustomerSubscription c where customerId =:customerId")
	List<CustomerSubscription> findByCustomerId(@Param("customerId") long customerId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM CustomerSubscription c where customerId =:customerId")
	void cancelCustomerSubscriptionByCustomerId(@Param("customerId") long customerId);

    List<CustomerSubscription> findBySubscriptionIdAndCustomerId(Long subscriptionId, Long customerId);

	List<CustomerSubscription> findBySubscriptionIdAndCustomerIdAndVin(String subscriptionId, String customerId,
			String vin);

	@Query(value = "SELECT COUNT(*) AS subscribers, DATE_FORMAT(customer_sub_startdate, '%b-%Y') AS period FROM customer_subscription where customer_sub_startdate >= date_sub(now(), INTERVAL 12 month) GROUP BY YEAR(customer_sub_startdate) ASC, MONTH(customer_sub_startdate) ASC;", nativeQuery = true)
	List<MonthlySubscribers> generateMonthlySubscriptionsReport();

	@Query(value = "SELECT subscriptionname, COUNT(*) AS subscribers, DATE_FORMAT(customer_sub_startdate, '%b-%Y') AS period FROM customer_subscription where customer_sub_startdate >= date_sub(now(), INTERVAL 12 month) GROUP BY subscriptionname, YEAR(customer_sub_startdate) ASC, MONTH(customer_sub_startdate) ASC;", nativeQuery = true)
	List<MonthlySubscribers> generateMonthlySubscriptionsPerSubscriptionReport();

}
