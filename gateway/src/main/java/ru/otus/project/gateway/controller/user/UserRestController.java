package ru.otus.project.gateway.controller.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.service.user.UserAuthenticationService;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserAuthenticationService service;

    @GetMapping("/checkAccess")
    public User checkAccess(Authentication authentication) {
        // TODO говнокод
        return service.authenticatedUser(authentication).orElse(null);
    }
}
