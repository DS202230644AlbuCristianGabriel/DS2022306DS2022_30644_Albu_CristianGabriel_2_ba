package com.company.my_app.user;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import javax.validation.Valid;

import com.company.my_app.JWebToken;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

    private final UserService userService;

    public UserResource(final UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final Long id) {
        return ResponseEntity.ok(userService.get(id));
    }
    @PostMapping
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UserDTO userDTO) {
        return new ResponseEntity<>(userService.create(userDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final Long id,
            @RequestBody @Valid final UserDTO userDTO) {
        userService.update(id, userDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("/getRefreshToken")
    public String getRefreshToken(@RequestBody @Valid final UserDTO userDTO) throws NoSuchAlgorithmException {
        int EXPIRY_DAYS = 200;
        JSONObject jwtPayload = new JSONObject();
        jwtPayload.put("status", 0);
        JSONArray audArray = new JSONArray();
        audArray.put("admin");
        jwtPayload.put("sub", userDTO.getId().toString());
        jwtPayload.put("aud", audArray);
        LocalDateTime ldt = LocalDateTime.now().plusDays(EXPIRY_DAYS);
        jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured
        String token = new JWebToken(jwtPayload).toString();
        return token;
    }

    @PostMapping("/getAccessToken")
    public String getAccessToken(@RequestBody @Valid final UserDTO userDTO) throws NoSuchAlgorithmException {
        int EXPIRY_MINUTES = 10;
        JSONObject jwtPayload = new JSONObject();
        jwtPayload.put("status", 0);
        JSONArray audArray = new JSONArray();
        audArray.put("admin");
        jwtPayload.put("sub", userDTO.getId().toString());
        jwtPayload.put("aud", audArray);
        LocalDateTime ldt = LocalDateTime.now().plusMinutes(EXPIRY_MINUTES);
        jwtPayload.put("exp", ldt.toEpochSecond(ZoneOffset.UTC)); //this needs to be configured
        String token = new JWebToken(jwtPayload).toString();
        return token;
    }

    @PostMapping("/isValidToken")
    public boolean isValidToken(@RequestBody @Valid final String token) throws NoSuchAlgorithmException {
        return new JWebToken(token).isValid();
    }

    @PostMapping("/getSubjectToken")
    public String getSubjectToken(@RequestBody @Valid final String token) throws NoSuchAlgorithmException {
        return new JWebToken(token).getSubject();
    }

    @PostMapping("/getAudienceToken")
    public List<String> getAudienceToken(@RequestBody @Valid final String token) throws NoSuchAlgorithmException {
        return new JWebToken(token).getAudience();
    }
    @PostMapping("/getByName")
    public ResponseEntity<UserDTO> getUserByName(@RequestParam @Valid final String username) {
        UserDTO userDTO = (UserDTO) userService.findAll().stream().filter(user -> user.getUsername().equals(username));
        return ResponseEntity.ok(userService.get(userDTO.getId()));
    }
}
