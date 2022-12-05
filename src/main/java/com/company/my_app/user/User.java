package com.company.my_app.user;

import com.company.my_app.smart_device.SmartDevice;
import java.time.LocalDate;
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
public class User {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "\"role\"")
    @Enumerated(EnumType.STRING)
    private Roles role;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String address;

    @Column
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Column
    private LocalDate birthDate;

    @OneToMany(mappedBy = "userDevices", cascade= CascadeType.ALL, orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<SmartDevice> userDevicesSmartDevices;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(final Roles role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Set<SmartDevice> getUserDevicesSmartDevices() {
        return userDevicesSmartDevices;
    }

    public void setUserDevicesSmartDevices(final Set<SmartDevice> userDevicesSmartDevices) {
        this.userDevicesSmartDevices = userDevicesSmartDevices;
    }

}
