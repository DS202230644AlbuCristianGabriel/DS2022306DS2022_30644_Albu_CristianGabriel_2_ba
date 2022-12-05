package com.company.my_app.registred_smart_energy;

import com.company.my_app.smart_device.SmartDevice;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class RegistredSmartEnergy {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private Timestamp timestamp;

    @Column
    private Double measurement_value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "device_registred_smart_energy_id")
    private SmartDevice deviceRegistredSmartEnergy;

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

    public SmartDevice getDeviceRegistredSmartEnergy() {
        return deviceRegistredSmartEnergy;
    }

    public void setDeviceRegistredSmartEnergy(final SmartDevice deviceRegistredSmartEnergy) {
        this.deviceRegistredSmartEnergy = deviceRegistredSmartEnergy;
    }

}
