package com.ownersite.rdr.dto;

public class VehiclesDTO {

    private String vehicleId;

    private String vehicleName;

    private String model;

    private String make;

    private String year;

    private String vin;

    private String registeredNumber;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
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

    @Override
    public String toString() {
        return "VehiclesDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", model='" + model + '\'' +
                ", make='" + make + '\'' +
                ", year='" + year + '\'' +
                ", vin='" + vin + '\'' +
                ", registeredNumber='" + registeredNumber + '\'' +
                '}';
    }
}