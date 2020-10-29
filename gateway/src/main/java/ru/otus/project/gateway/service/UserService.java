package ru.otus.project.gateway.service;

import org.springframework.security.core.Authentication;
import ru.otus.project.gateway.dto.security.User;

import java.util.Optional;

public interface UserService {
    Optional<User> authenticatedUser(Authentication authentication);
}
