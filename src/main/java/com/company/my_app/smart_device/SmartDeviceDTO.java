package com.company.my_app.smart_device;

import javax.validation.constraints.Size;


public class SmartDeviceDTO {

    private Long id;

    @Size(max = 255)
    private String description;

    @Size(max = 255)
    private String address;

    private Long maxHourlyEnergyConsumption;

    private Long userDevices;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public Long getMaxHourlyEnergyConsumption() {
        return maxHourlyEnergyConsumption;
    }

    public void setMaxHourlyEnergyConsumption(final Long maxHourlyEnergyConsumption) {
        this.maxHourlyEnergyConsumption = maxHourlyEnergyConsumption;
    }

    public Long getUserDevices() {
        return userDevices;
    }

    public void setUserDevices(final Long userDevices) {
        this.userDevices = userDevices;
    }

}
