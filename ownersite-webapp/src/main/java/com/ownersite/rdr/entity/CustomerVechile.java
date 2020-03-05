/**
 * 
 */
package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author polamred
 *
 */
@Entity
@Cacheable(false)
@Table(name = "CustomerVechile")
public class CustomerVechile extends BaseEntity{

	private String vin;
	private Long vechileId;
	private Long customerId;
	private Long dealerId;
	private String registeredNumber;

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Long getVechileId() {
		return vechileId;
	}
	public void setVechileId(Long vechileId) {
		this.vechileId = vechileId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getDealerId() {
		return dealerId;
	}
	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getRegisteredNumber() {
		return registeredNumber;
	}

	public void setRegisteredNumber(String registeredNumber) {
		this.registeredNumber = registeredNumber;
	}

	@Override
	public String toString() {
		return "CustomerVechile [vin=" + vin + ", vechileId=" + vechileId + ", customerId=" + customerId + ", dealerId="
				+ dealerId + "]";
	}
	
}
