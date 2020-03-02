package com.ownersite.rdr.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Cacheable(false)
@Table(name = "CustomerVinRdr")
public class CustomerVinRdr extends BaseEntity{
	private java.util.Date rdr_confirmed_date;

	public java.util.Date getRdr_confirmed_date(){
		return rdr_confirmed_date;
	}

	public void setRdr_confirmed_date(java.util.Date rdr_confirmed_date){
		this.rdr_confirmed_date=rdr_confirmed_date;
	}
}