/**
 * 
 */
package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ownersite.rdr.entity.ServiceStation;

/**
 * @author polamred
 *
 */
public interface ServiceStationJpaRepository extends JpaRepository<ServiceStation, Long>{

	@Query(value = "SELECT c from ServiceStation c where id =:id")
	ServiceStation findByServiceStationId(@Param("id") long serviceStationId);
}
