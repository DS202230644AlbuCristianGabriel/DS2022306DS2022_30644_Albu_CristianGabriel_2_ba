package com.company.my_app.registred_smart_energy;

import java.sql.Timestamp;


public class RegistredSmartEnergyDTO {

    private String id;
    private Timestamp timestamp;
    private Double measurement_value;
    private Integer deviceRegistredSmartEnergy;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getMeasurement_value() {
        return measurement_value;
    }

    public void setMeasurement_value(Double measurement_value) {
        this.measurement_value = measurement_value;
    }
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

    public Integer getDeviceRegistredSmartEnergy() {
        return deviceRegistredSmartEnergy;
    }

    public void setDeviceRegistredSmartEnergy(final Integer deviceRegistredSmartEnergy) {
        this.deviceRegistredSmartEnergy = deviceRegistredSmartEnergy;
    }

}
