/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.CustomerFeedback;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerFeedbackJpaRepository extends JpaRepository<CustomerFeedback, Long>{

    List<CustomerFeedback> findByCustomerId(String customerId);

    List<CustomerFeedback> findByDealerId(String dealerId);
}
