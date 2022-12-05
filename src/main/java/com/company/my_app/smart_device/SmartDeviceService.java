package com.company.my_app.smart_device;

import com.company.my_app.user.User;
import com.company.my_app.user.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SmartDeviceService {

    private final SmartDeviceRepository smartDeviceRepository;
    private final UserRepository userRepository;

    public SmartDeviceService(final SmartDeviceRepository smartDeviceRepository,
            final UserRepository userRepository) {
        this.smartDeviceRepository = smartDeviceRepository;
        this.userRepository = userRepository;
    }

    public List<SmartDeviceDTO> findAll() {
        return smartDeviceRepository.findAll(Sort.by("id"))
                .stream()
                .map(smartDevice -> mapToDTO(smartDevice, new SmartDeviceDTO()))
                .collect(Collectors.toList());
    }

    public SmartDeviceDTO get(final Long id) {
        return smartDeviceRepository.findById(id)
                .map(smartDevice -> mapToDTO(smartDevice, new SmartDeviceDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final SmartDeviceDTO smartDeviceDTO) {
        final SmartDevice smartDevice = new SmartDevice();
        mapToEntity(smartDeviceDTO, smartDevice);
        return smartDeviceRepository.save(smartDevice).getId();
    }

    public void update(final Long id, final SmartDeviceDTO smartDeviceDTO) {
        final SmartDevice smartDevice = smartDeviceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(smartDeviceDTO, smartDevice);
        smartDeviceRepository.save(smartDevice);
    }

    public void delete(final Long id) {
        smartDeviceRepository.deleteById(id);
    }

    private SmartDeviceDTO mapToDTO(final SmartDevice smartDevice,
            final SmartDeviceDTO smartDeviceDTO) {
        smartDeviceDTO.setId(smartDevice.getId());
        smartDeviceDTO.setDescription(smartDevice.getDescription());
        smartDeviceDTO.setAddress(smartDevice.getAddress());
        smartDeviceDTO.setMaxHourlyEnergyConsumption(smartDevice.getMaxHourlyEnergyConsumption());
        smartDeviceDTO.setUserDevices(smartDevice.getUserDevices() == null ? null : smartDevice.getUserDevices().getId());
        return smartDeviceDTO;
    }

    private SmartDevice mapToEntity(final SmartDeviceDTO smartDeviceDTO,
            final SmartDevice smartDevice) {
        smartDevice.setDescription(smartDeviceDTO.getDescription());
        smartDevice.setAddress(smartDeviceDTO.getAddress());
        smartDevice.setMaxHourlyEnergyConsumption(smartDeviceDTO.getMaxHourlyEnergyConsumption());
        final User userDevices = smartDeviceDTO.getUserDevices() == null ? null : userRepository.findById(smartDeviceDTO.getUserDevices())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userDevices not found"));
        smartDevice.setUserDevices(userDevices);
        return smartDevice;
    }

    public List<SmartDeviceDTO> getAllForUser(Long id) {
        return smartDeviceRepository.findAll(Sort.by("id"))
                .stream().filter(device -> device.getUserDevices().getId() == id)
                .map(smartDevice -> mapToDTO(smartDevice, new SmartDeviceDTO()))
                .collect(Collectors.toList());
    }
}
