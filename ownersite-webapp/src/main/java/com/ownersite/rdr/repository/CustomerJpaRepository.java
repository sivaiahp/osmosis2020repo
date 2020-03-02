/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.Customer;

/**
 * @author polamred
 *
 */
public interface CustomerJpaRepository extends JpaRepository<Customer, Long>{

}
