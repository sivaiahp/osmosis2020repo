package com.ownersite.rdr.dto;

public class CustomerCarsDTO {
    private String vehicleId;
    private String vin;
    private String registationNo;
    private String maker;
    private String model;
    private String year;

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getRegistationNo() {
        return registationNo;
    }

    public void setRegistationNo(String registationNo) {
        this.registationNo = registationNo;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "CustomerCarsDTO{" +
                "vehicleId='" + vehicleId + '\'' +
                ", vin='" + vin + '\'' +
                ", registationNo='" + registationNo + '\'' +
                ", maker='" + maker + '\'' +
                ", model='" + model + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
