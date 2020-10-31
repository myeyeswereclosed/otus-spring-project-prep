package ru.otus.project.gateway.service.user;

import org.springframework.http.ResponseEntity;
import ru.otus.project.gateway.dto.security.TokenResponseDto;
import ru.otus.project.gateway.dto.security.User;

import java.util.Map;

public interface UserService {
    TokenResponseDto accessToken(String username, String password);

    ResponseEntity<?> register(User user);
}
