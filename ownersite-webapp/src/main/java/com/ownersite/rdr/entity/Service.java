package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "Service")
public class Service extends BaseEntity{
	private String servicename;
	private String servicedec;

	public String getServicename(){
		return servicename;
	}

	public void setServicename(String servicename){
		this.servicename=servicename;
	}

	public String getServicedec(){
		return servicedec;
	}

	public void setServicedec(String servicedec){
		this.servicedec=servicedec;
	}

	@Override
	public String toString() {
		return "Service{" +
				"servicename='" + servicename + '\'' +
				", servicedec='" + servicedec + '\'' +
				'}';
	}
}