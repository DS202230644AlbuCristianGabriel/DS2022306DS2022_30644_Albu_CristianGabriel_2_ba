package com.company.my_app.smart_device;

import com.company.my_app.registred_smart_energy.RegistredSmartEnergy;
import com.company.my_app.user.User;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class SmartDevice {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "\"description\"")
    private String description;

    @Column
    private String address;

    @Column
    private Long maxHourlyEnergyConsumption;

    @ManyToOne(fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
    @JoinColumn(name = "user_devices_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User userDevices;

    @OneToMany(mappedBy = "deviceRegistredSmartEnergy", cascade=CascadeType.ALL, orphanRemoval = true)
    private Set<RegistredSmartEnergy> deviceRegistredSmartEnergyRegistredSmartEnergys;

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

    public User getUserDevices() {
        return userDevices;
    }

    public void setUserDevices(final User userDevices) {
        this.userDevices = userDevices;
    }

    public Set<RegistredSmartEnergy> getDeviceRegistredSmartEnergyRegistredSmartEnergys() {
        return deviceRegistredSmartEnergyRegistredSmartEnergys;
    }

    public void setDeviceRegistredSmartEnergyRegistredSmartEnergys(
            final Set<RegistredSmartEnergy> deviceRegistredSmartEnergyRegistredSmartEnergys) {
        this.deviceRegistredSmartEnergyRegistredSmartEnergys = deviceRegistredSmartEnergyRegistredSmartEnergys;
    }

}
