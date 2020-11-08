package ru.otus.project.gateway.service.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.project.gateway.model.security.User;

@FeignClient(
    name = "authorization-server",
    path = "/auth-server",
    contextId = "register"
)
public interface AuthorizationServerRegistrationClient {
    @PostMapping("/user")
    ResponseEntity<User> register(@RequestBody User user);
}
