/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.CustomerEnquiry;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerEnquiryJpaRepository extends JpaRepository<CustomerEnquiry, Long>{

    List<CustomerEnquiry> findByCustomerId(String customerId);


    List<CustomerEnquiry> findByDealerId(String dealerId);
}
