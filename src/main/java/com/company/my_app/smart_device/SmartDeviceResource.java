package com.company.my_app.smart_device;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/smartDevices", produces = MediaType.APPLICATION_JSON_VALUE)
public class SmartDeviceResource {

    private final SmartDeviceService smartDeviceService;

    public SmartDeviceResource(final SmartDeviceService smartDeviceService) {
        this.smartDeviceService = smartDeviceService;
    }

    @GetMapping
    public ResponseEntity<List<SmartDeviceDTO>> getAllSmartDevices() {
        return ResponseEntity.ok(smartDeviceService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SmartDeviceDTO> getSmartDevice(@PathVariable final Long id) {
        return ResponseEntity.ok(smartDeviceService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createSmartDevice(
            @RequestBody @Valid final SmartDeviceDTO smartDeviceDTO) {
        return new ResponseEntity<>(smartDeviceService.create(smartDeviceDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSmartDevice(@PathVariable final Long id,
            @RequestBody @Valid final SmartDeviceDTO smartDeviceDTO) {
        smartDeviceService.update(id, smartDeviceDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSmartDevice(@PathVariable final Long id) {
        smartDeviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/userDevices/{id}")
    public ResponseEntity<List<SmartDeviceDTO>> getSmartDevices(@PathVariable final Long id) {
        return ResponseEntity.ok(smartDeviceService.getAllForUser(id));
    }
}
