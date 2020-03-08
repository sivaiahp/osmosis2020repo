package com.ownersite.rdr.dto;

import java.util.Date;

import com.ownersite.rdr.entity.Service;
import com.ownersite.rdr.entity.Vehicle;

public class VehiclesDTO {

    private String vehicleId;

    private String model;

    private String make;

    private String year;

    private String vin;

    private String registeredNumber;

    private String customerId;

    private Date rdrRegisteredDate;

    public VehiclesDTO() {
		super();
	}

	public VehiclesDTO(Vehicle vehicle) {
		super();
		this.setModel(vehicle.getModel());
		this.setMake(vehicle.getMake());
		this.setYear(vehicle.getYear());
		
	}

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getRdrRegisteredDate() {
        return rdrRegisteredDate;
    }

    public void setRdrRegisteredDate(Date rdrRegisteredDate) {
        this.rdrRegisteredDate = rdrRegisteredDate;
    }

    @Override
    public String toString() {
        return "VehiclesDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", year='" + year + '\'' +
                ", vin='" + vin + '\'' +
                ", registeredNumber='" + registeredNumber + '\'' +
                '}';
    }
}
