package ru.otus.project.gateway.service.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.project.gateway.dto.security.User;

@FeignClient(
    name = "rehearsal-base-auth-server",
    path = "/auth-server",
    contextId = "register"
)
public interface AuthorizationServerRegistrationClient {
    @PostMapping("register")
    ResponseEntity<?> register(@RequestBody User user);
}
