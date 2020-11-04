package ru.otus.project.gateway.service.user;

import org.springframework.security.core.Authentication;
import ru.otus.project.gateway.model.security.AccessToken;
import ru.otus.project.gateway.model.security.User;

import java.util.Optional;

public interface UserService {
    Optional<AccessToken> accessToken(String username, String password) throws InvalidCredentials;

    Optional<User> register(User user);

    Optional<User> authorizedUser(Authentication authentication);
}
