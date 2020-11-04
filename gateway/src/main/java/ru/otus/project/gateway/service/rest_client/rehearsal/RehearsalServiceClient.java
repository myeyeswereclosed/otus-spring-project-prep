package ru.otus.project.gateway.service.rest_client.rehearsal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.gateway.model.rehearsal.Rehearsal;

// TODO how to get path without hardcode
@FeignClient(name = "rehearsal-service", path = "/rehearsal-service")
public interface RehearsalServiceClient {
    @PostMapping("/artist/{phone}/rehearsal")
    Rehearsal reserve(@PathVariable String phone, @RequestBody Rehearsal rehearsal);

    @DeleteMapping("/rehearsal/{id}")
    Rehearsal cancel(@PathVariable long id);
}
