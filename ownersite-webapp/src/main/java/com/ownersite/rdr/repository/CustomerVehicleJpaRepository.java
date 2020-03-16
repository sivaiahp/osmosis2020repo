/**
 * 
 */
package com.ownersite.rdr.repository;

import com.ownersite.rdr.entity.CustomerVechile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author polamred
 *
 */
public interface CustomerVehicleJpaRepository extends JpaRepository<CustomerVechile, Long> {

	List<CustomerVechile> findByCustomerId(long customerId);

	List<CustomerVechile> findByCustomerId(String customerId);

	CustomerVechile findByVin(String vin);

	@Query(value = "SELECT CONCAT_WS(' ', firstname, lastname) AS name, days, period FROM dealer AS d JOIN (SELECT DEALER_ID, CEILING(AVG(days)) AS Days, DATE_FORMAT(rdr_registered_date, '%b-%Y') AS period\n"
			+ "FROM (\n"
			+ "SELECT dealer_id, DATEDIFF(dealer_rdr_registered_date, rdr_registered_date) AS days, rdr_registered_date\n"
			+ "FROM customer_vechile\n"
			+ "WHERE dealer_rdr_registered_date IS NOT NULL AND rdr_registered_date >= DATE_SUB(NOW(), INTERVAL 12 MONTH)) AS cs\n"
			+ "GROUP BY DEALER_ID, YEAR(rdr_registered_date) ASC, MONTH(rdr_registered_date) ASC) AS r ON d.id = r.dealer_id", nativeQuery = true)
	List<RDRReport> generateRDRReport();

	public interface RDRReport {
		public String getName();

		public String getDays();

		public String getPeriod();
	}

}
