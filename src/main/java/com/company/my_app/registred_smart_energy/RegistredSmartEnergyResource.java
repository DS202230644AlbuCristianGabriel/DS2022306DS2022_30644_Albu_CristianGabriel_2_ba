package com.company.my_app.registred_smart_energy;

import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://jolly-tree-09eef8403.2.azurestaticapps.net")
@RestController
@RequestMapping(value = "/api/registredSmartEnergy", produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class RegistredSmartEnergyResource {
//    @Autowired
    private final SimpMessagingTemplate messageSender;
    private final RegistredSmartEnergyService registredSmartEnergyService;

    public RegistredSmartEnergyResource(
            SimpMessagingTemplate messageSender, final RegistredSmartEnergyService registredSmartEnergyService) {
        this.messageSender = messageSender;
        this.registredSmartEnergyService = registredSmartEnergyService;
    }

    @GetMapping
    public ResponseEntity<List<RegistredSmartEnergyDTO>> getAllRegistredSmartEnergys() {
        return ResponseEntity.ok(registredSmartEnergyService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistredSmartEnergyDTO> getRegistredSmartEnergy(
            @PathVariable final Long id) {
        return ResponseEntity.ok(registredSmartEnergyService.get(id));
    }

    @PostMapping
    public ResponseEntity<String> createRegistredSmartEnergy(
            @RequestBody @Valid final RegistredSmartEnergyDTO registredSmartEnergyDTO) {
        return new ResponseEntity<>(registredSmartEnergyService.create(registredSmartEnergyDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRegistredSmartEnergy(@PathVariable final Long id,
            @RequestBody @Valid final RegistredSmartEnergyDTO registredSmartEnergyDTO) {
        registredSmartEnergyService.update(id, registredSmartEnergyDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistredSmartEnergy(@PathVariable final Long id) {
        registredSmartEnergyService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/devicesEnergy/{date}/{id}")
    public ResponseEntity<List<RegistredSmartEnergyDTO>> getSmartDevices(@PathVariable final Long id, @PathVariable final String date) {
        return ResponseEntity.ok(registredSmartEnergyService.getAllForDevice(id, date));
    }
    @EventListener(Double.class)
    public void handleEvent(Double event) {
        System.out.println("kk" + event);
        messageSender.convertAndSend("/topic/consumption", event);
    }
}
