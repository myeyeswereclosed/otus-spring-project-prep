package ru.otus.project.gateway.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.gateway.config.FeignClientConfig;
import ru.otus.project.gateway.dto.security.TokenResponseDto;

import java.util.Map;

@FeignClient(
    name = "rehearsal-base-auth-server",
    path = "/auth-server",
    contextId = "token",
    configuration = FeignClientConfig.class
)
public interface AuthorizationServerTokenClient {
    @PostMapping("oauth/token")
    TokenResponseDto accessToken(
        @RequestParam("grant_type") String grantType, @RequestParam("username") String username,
        @RequestParam("password") String password, @RequestParam("client_id") String clientId
//        @RequestHeader HttpHeaders headers,
//        @RequestBody Map<String, ?> formData
    );
}
