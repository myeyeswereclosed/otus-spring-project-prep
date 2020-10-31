package ru.otus.project.gateway.service.user;

import org.springframework.security.core.Authentication;
import ru.otus.project.gateway.dto.security.User;

import java.util.Optional;

public interface UserAuthenticationService {
    Optional<User> authenticatedUser(Authentication authentication);
}
