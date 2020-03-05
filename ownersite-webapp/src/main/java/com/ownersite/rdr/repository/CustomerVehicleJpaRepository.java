/**
 * 
 */
package com.ownersite.rdr.repository;

import com.ownersite.rdr.entity.CustomerVechile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerVehicleJpaRepository extends JpaRepository<CustomerVechile, Long>{

	List<CustomerVechile> findByCustomer(String customerId);
}
