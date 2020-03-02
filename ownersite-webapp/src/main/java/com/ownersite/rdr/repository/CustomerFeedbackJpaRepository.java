/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.CustomerFeedback;

/**
 * @author polamred
 *
 */
public interface CustomerFeedbackJpaRepository extends JpaRepository<CustomerFeedback, Long>{

}
