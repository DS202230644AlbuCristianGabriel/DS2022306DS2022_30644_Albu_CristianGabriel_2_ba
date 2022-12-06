package com.company.my_app.registred_smart_energy;

import com.company.my_app.smart_device.SmartDevice;
import com.company.my_app.smart_device.SmartDeviceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RegistredSmartEnergyService {

    private final RegistredSmartEnergyRepository registredSmartEnergyRepository;
    private final SmartDeviceRepository smartDeviceRepository;

    public RegistredSmartEnergyService(
            final RegistredSmartEnergyRepository registredSmartEnergyRepository,
            final SmartDeviceRepository smartDeviceRepository) {
        this.registredSmartEnergyRepository = registredSmartEnergyRepository;
        this.smartDeviceRepository = smartDeviceRepository;
    }

    public List<RegistredSmartEnergyDTO> findAll() {
        return registredSmartEnergyRepository.findAll(Sort.by("id"))
                .stream()
                .map(registredSmartEnergy -> mapToDTO(registredSmartEnergy, new RegistredSmartEnergyDTO()))
                .collect(Collectors.toList());
    }

    public RegistredSmartEnergyDTO get(final Long id) {
        return registredSmartEnergyRepository.findById(id)
                .map(registredSmartEnergy -> mapToDTO(registredSmartEnergy, new RegistredSmartEnergyDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public String create(final RegistredSmartEnergyDTO registredSmartEnergyDTO) {
        final RegistredSmartEnergy registredSmartEnergy = new RegistredSmartEnergy();
        mapToEntity(registredSmartEnergyDTO, registredSmartEnergy);
        return registredSmartEnergyRepository.save(registredSmartEnergy).getId();
    }

    public void update(final Long id, final RegistredSmartEnergyDTO registredSmartEnergyDTO) {
        final RegistredSmartEnergy registredSmartEnergy = registredSmartEnergyRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(registredSmartEnergyDTO, registredSmartEnergy);
        registredSmartEnergyRepository.save(registredSmartEnergy);
    }

    public void delete(final Long id) {
        registredSmartEnergyRepository.deleteById(id);
    }

    private RegistredSmartEnergyDTO mapToDTO(final RegistredSmartEnergy registredSmartEnergy,
            final RegistredSmartEnergyDTO registredSmartEnergyDTO) {
        registredSmartEnergyDTO.setId(registredSmartEnergy.getId());
        registredSmartEnergyDTO.setTimestamp(registredSmartEnergy.getDate());
        registredSmartEnergyDTO.setMeasurement_value(registredSmartEnergy.getEnergyConsumed());
        registredSmartEnergyDTO.setDeviceRegistredSmartEnergy(Math.toIntExact(registredSmartEnergy.getDeviceRegistredSmartEnergy() == null ? null : registredSmartEnergy.getDeviceRegistredSmartEnergy().getId()));
        return registredSmartEnergyDTO;
    }

    private RegistredSmartEnergy mapToEntity(final RegistredSmartEnergyDTO registredSmartEnergyDTO,
            final RegistredSmartEnergy registredSmartEnergy) {
        registredSmartEnergy.setDate(registredSmartEnergyDTO.getTimestamp());
        registredSmartEnergy.setEnergyConsumed(registredSmartEnergyDTO.getMeasurement_value());
        final SmartDevice deviceRegistredSmartEnergy = registredSmartEnergyDTO.getDeviceRegistredSmartEnergy() == null ? null : smartDeviceRepository.findById(Long.valueOf(registredSmartEnergyDTO.getDeviceRegistredSmartEnergy().toString()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "deviceRegistredSmartEnergy not found"));
        registredSmartEnergy.setDeviceRegistredSmartEnergy(deviceRegistredSmartEnergy);
        return registredSmartEnergy;
    }

    public List<RegistredSmartEnergyDTO> getAllForDevice(Long id, String date) {
        return registredSmartEnergyRepository.findAll(Sort.by("id"))
                .stream()
                .filter(energy -> energy.getDeviceRegistredSmartEnergy().getId() == id)
                .filter(energy -> energy.getDate().toString().split(" ")[0].equals(date))
                .map(registredSmartEnergy -> mapToDTO(registredSmartEnergy, new RegistredSmartEnergyDTO()))
                .collect(Collectors.toList());
    }
}
