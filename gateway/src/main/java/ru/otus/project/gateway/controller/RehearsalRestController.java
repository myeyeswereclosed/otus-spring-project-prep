package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.gateway.dto.rehearsal.RehearsalDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.service.UserService;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RehearsalRestController {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalRestController.class);

    private final UserService service;

    private final RestTemplate restClient = new RestTemplate();
    private final String url = "http://localhost:8888";

    @PostMapping("/rehearsal")
    public ResponseEntity<?> reserve(@RequestBody RehearsalDto rehearsal, Authentication authentication) {
//        if (rehearsal.isValid())

        // TODO separate to strategies: user, admin ?

        var response =
            service
                .authenticatedUser(authentication)
                .map(user -> reserve(user, rehearsal))
                .orElse(ResponseEntity.badRequest().build())
        ;

        logger.info("Response IS " + response);

        return response;
    }

    private ResponseEntity<RehearsalDto> reserve(User user, RehearsalDto rehearsal) {
        logger.info(
            "Trying to reserve rehearsal: room: {}, {}({} hour(s)) for {}({})",
            rehearsal.getRoom().getName(),
            rehearsal.getStartsAt(),
            rehearsal.getDuration(),
            user.getName(),
            user.getPhone()
        );

        var response =
            restClient.postForEntity(
                url + "/artist/" + user.getPhone() + "/rehearsal",
                rehearsal,
                RehearsalDto.class
            );

        logger.info("Response from rehearsal service: {}", response);

        return response;
    }

    @DeleteMapping("rehearsal/{id}")
    public ResponseEntity<?> cancel(@PathVariable long id) {
        try {
            restClient.delete(url + "/rehearsal/" + id);

            return ok().build();
        } catch (Exception e) {
            logger.info("Exception: {}, trace:\r\n{}", e.getMessage(), getStackTrace(e));

            return status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/rehearsals")
    public ResponseEntity<List<RehearsalDto>> getArtistRehearsals(Authentication authentication) {
        return
            ok(
                service
                    .authenticatedUser(authentication)
                    .map(
                        user -> {
                            logger.info("Trying to obtain rehearsals for user {}", user);

                            return
                                restClient.exchange(
                                    url + "/artist/" + user.getPhone() + "/rehearsals",
                                    GET,
                                    null,
                                    new ParameterizedTypeReference<List<RehearsalDto>>() {}
                                ).getBody();
                        })
                    .orElse(Collections.emptyList())
            )
        ;
    }

//    @GetMapping("/rehearsal/{id}")
//    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
//        return ResponseEntity.of(service.get(id));
//    }
}
