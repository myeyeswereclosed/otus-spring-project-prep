package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.service.UserService;

@SuppressWarnings("deprecation")
@RestController
@RequiredArgsConstructor
public class SecurityController {
    private static final Logger logger = LoggerFactory.getLogger(SecurityController.class);

    private final UserService service;

    @GetMapping("/checkAccess")
    public User checkAccess(Authentication authentication) {
        // TODO говнокод
        return service.authenticatedUser(authentication).orElse(null);
    }
}
