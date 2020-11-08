package ru.otus.project.authorization_server.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.authorization_server.domain.RehearsalBaseUser;
import ru.otus.project.authorization_server.domain.User;
import ru.otus.project.authorization_server.dto.UserDto;
import ru.otus.project.authorization_server.service.registration.RegistrationService;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserDetailsService userService;
    private final RegistrationService service;
    private final PasswordEncoder encoder;

    @PostMapping("/user")
    public ResponseEntity<UserDto> register(@RequestBody UserDto dto) {
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

    @GetMapping("/user")
    public ResponseEntity<String> get(@RequestParam String phone) {
        logger.info("Trying to find user by phone {}", phone);

        try {
            var user = ((RehearsalBaseUser)userService.loadUserByUsername(phone));

            logger.info("Found {}", user);

            return ok(phone);
        } catch (UsernameNotFoundException e) {
            logger.info("User with phone {} not found", phone);
        }

        return notFound().build();
    }
}
