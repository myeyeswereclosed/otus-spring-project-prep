package ru.otus.project.gateway.service.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.project.gateway.config.FeignClientConfig;
import ru.otus.project.gateway.model.security.AccessToken;

// TODO сделать аккуратнее
@FeignClient(
    name = "authorization-server",
    path = "/auth-server",
    contextId = "token",
    configuration = FeignClientConfig.class
)
public interface AuthorizationServerTokenClient {
    @PostMapping("oauth/token")
    AccessToken accessToken(
        @RequestParam("grant_type") String grantType,
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        @RequestParam("client_id") String clientId
    );
}
