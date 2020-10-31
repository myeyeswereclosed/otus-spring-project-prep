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
import ru.otus.project.gateway.service.AuthorizationServerRegistrationClient;
import ru.otus.project.gateway.service.AuthorizationServerTokenClient;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthorizationServerTokenClient tokenClient;
    private final AuthorizationServerRegistrationClient registrationClient;
    private final AuthorizationConfig config;

    public TokenResponseDto accessToken(String username, String password) {
//        return tokenClient.accessToken(/*headersForTokenRequest(), */formDataForTokenRequest(username, password));
        return tokenClient.accessToken("password", username, password, config.getClientId());
    }

    @Override
    public ResponseEntity<?> register(User user) {
        return registrationClient.register(user);
    }

    private HttpHeaders headersForTokenRequest() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(config.getClientId(), config.getClientSecret());

        return headers;
    }

    private MultiValueMap<String, String> formDataForTokenRequest(String username, String password) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("username", username);
        formData.add("password", password);
        formData.add("client_id", config.getClientId());
        formData.add("grant_type", "password");

        return formData;
    }
}
