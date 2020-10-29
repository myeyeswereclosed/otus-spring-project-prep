package ru.otus.project.authorization_server.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.authorization_server.dto.RehearsalBaseUserDto;
import ru.otus.project.authorization_server.model.User;
import ru.otus.project.authorization_server.service.registration.RegistrationService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.badRequest;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final RegistrationService service;
    private final PasswordEncoder encoder;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RehearsalBaseUserDto dto) {
        logger.info("Trying to register {}", dto);

        try {
            var user =
                service
                    .register(
                        new User()
                            .setName(dto.getName())
                            .setPhone(dto.getPhone())
                            .setEmail(dto.getEmail())
                            .setPassword(encoder.encode(dto.getPassword()))
                            .setRole(dto.getRole())
                    );

            logger.info("New user {} was successfully registered", user);

            return status(CREATED).body(dto);
        } catch (Exception e) {
            return badRequest().build();
        }
    }
}
