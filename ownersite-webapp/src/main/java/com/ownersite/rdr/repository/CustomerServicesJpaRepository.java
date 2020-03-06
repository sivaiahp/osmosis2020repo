/**
 * 
 */
package com.ownersite.rdr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.Customer;
import com.ownersite.rdr.entity.CustomerServices;

/**
 * @author polamred
 *
 */
public interface CustomerServicesJpaRepository  extends JpaRepository<CustomerServices, Long>{

    List<CustomerServices> findByCustomer(Customer customer);

    @Query(value = "SELECT c from CustomerServices c where dealerId =:dealerId")
    List<CustomerServices> findByDealerId(@Param("dealerId")long dealerId);

}
