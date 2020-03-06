package com.ownersite.rdr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ownersite.rdr.entity.Vehicle;

import java.util.List;

public interface VehicleJpaRepository extends JpaRepository<Vehicle, Long>{

}
