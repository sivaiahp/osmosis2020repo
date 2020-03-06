/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.Customer;

/**
 * @author polamred
 *
 */
public interface CustomerJpaRepository extends JpaRepository<Customer, Long>{

	@Query(value = "SELECT c from Customer c where id =:id")
	Customer findByCustomerId(@Param("id") long id);
}
