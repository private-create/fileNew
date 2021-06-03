package com.test.fileupload.DTO;

public class DriverImageDTO {


    private String stationId;

    private String driverImage;


    private String driverBmp;

    private String identityId;

    private String type;

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getDriverImage() {
        return driverImage;
    }

    public void setDriverImage(String driverImage) {
        this.driverImage = driverImage;
    }

    public String getDriverBmp() {
        return driverBmp;
    }

    public void setDriverBmp(String driverBmp) {
        this.driverBmp = driverBmp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }
}
