/**
 * 
 */
package com.ownersite.rdr.repository;

import com.ownersite.rdr.service.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ownersite.rdr.entity.Customer;
import com.ownersite.rdr.entity.CustomerServices;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerServicesJpaRepository  extends JpaRepository<CustomerServices, Long>{

    List<CustomerService> findByCustomer(Customer customer);
}
