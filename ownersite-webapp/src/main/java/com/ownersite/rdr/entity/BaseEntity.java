package com.ownersite.rdr.entity;

import javax.persistence.*;

@MappedSuperclass
@Cacheable(false)
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BaseEntity{" +
				"id=" + id +
				'}';
	}
}
