package ru.otus.project.gateway.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.otus.project.gateway.config.AuthorizationConfig;
import ru.otus.project.gateway.dto.security.TokenResponseDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.service.rest_client.AuthorizationServerRegistrationClient;
import ru.otus.project.gateway.service.rest_client.AuthorizationServerTokenClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthorizationServerTokenClient tokenClient;
    private final AuthorizationServerRegistrationClient registrationClient;
    private final AuthorizationConfig config;

    public TokenResponseDto accessToken(String username, String password) {
        return tokenClient.accessToken("password", username, password, config.getClientId());
    }

    @Override
    public ResponseEntity<?> register(User user) {
        return registrationClient.register(user);
    }
}
