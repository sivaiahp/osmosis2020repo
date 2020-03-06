package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.Vehicle;

public interface VehicleJpaRepository extends JpaRepository<Vehicle, Long>{

}
