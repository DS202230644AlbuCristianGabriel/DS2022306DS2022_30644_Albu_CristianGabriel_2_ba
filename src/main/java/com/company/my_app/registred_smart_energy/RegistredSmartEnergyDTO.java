package com.company.my_app.registred_smart_energy;

import java.sql.Timestamp;


public class RegistredSmartEnergyDTO {

    private String id;
    private Timestamp timestamp;
    private Double measurement_value;
    private Integer deviceRegistredSmartEnergy;

//    public RegistredSmartEnergyDTO(HttpEntity<String> request) {
//        this.deviceRegistredSmartEnergy = (Long) new JSONObject(request.getBody()).get("device_id");
//        this.measurement_value = (Long) new JSONObject(request.getBody()).get("measurement_value");
//        this.timestamp = (Timestamp) new JSONObject(request.getBody()).get("timestamp");
//    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public Timestamp getDate() {
        return timestamp;
    }

    public void setDate(final Timestamp date) {
        this.timestamp = date;
    }

    public Double getEnergyConsumed() {
        return measurement_value;
    }

    public void setEnergyConsumed(final Double energyConsumed) {
        this.measurement_value = energyConsumed;
    }

    public Integer getDeviceRegistredSmartEnergy() {
        return deviceRegistredSmartEnergy;
    }

    public void setDeviceRegistredSmartEnergy(final Integer deviceRegistredSmartEnergy) {
        this.deviceRegistredSmartEnergy = deviceRegistredSmartEnergy;
    }

}
