/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.Dealer;

/**
 * @author polamred
 *
 */
public interface DealerJpaRepository extends JpaRepository<Dealer, Long>{

	@Query(value = "SELECT c from Dealer c where id =:dealerId")
	Dealer findByDealerId(@Param("dealerId") long dealerId);
}
