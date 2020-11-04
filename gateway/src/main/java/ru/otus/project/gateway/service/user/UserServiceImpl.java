package ru.otus.project.gateway.service.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.config.AuthorizationConfig;
import ru.otus.project.gateway.dto.security.TokenResponseDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.service.rest_client.AuthorizationServerRegistrationClient;
import ru.otus.project.gateway.service.rest_client.AuthorizationServerTokenClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final AuthorizationServerTokenClient tokenClient;
    private final AuthorizationServerRegistrationClient registrationClient;
    private final AuthorizationConfig config;

    public TokenResponseDto accessToken(String username, String password) {
        logger.info("Requesting access token for {}", username);

        return tokenClient.accessToken("password", username, password, config.getClientId());
    }

    @Override
    public ResponseEntity<?> register(User user) {
        return registrationClient.register(user);
    }
}
