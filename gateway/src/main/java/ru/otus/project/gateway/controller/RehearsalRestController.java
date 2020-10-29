package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.gateway.dto.RehearsalDto;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RehearsalRestController {
    private static Logger logger = LoggerFactory.getLogger(RehearsalRestController.class);

    private final RestTemplate restClient = new RestTemplate();
    private final String url = "http://localhost:8888/";

    @PostMapping("/rehearsal")
    public ResponseEntity<?> reserve(@RequestBody RehearsalDto rehearsal, Authentication authentication) {
//        if (rehearsal.isValid())

        logger.info("POST AUTHENTICATION " + authentication);

        var response =
            restClient.postForEntity(
                url + "/rehearsal",
                rehearsal,
                RehearsalDto.class
            );

        logger.info("Response IS " + response);

        return response;
    }


//    @DeleteMapping("rehearsal/{id}")
//    public ResponseEntity<?> cancel(@PathVariable long id) {
//        try {
//            return ResponseEntity.of(service.cancel(id));
//        } catch (TooLateToCancel e) {
//            return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
//        }
//    }

//    @GetMapping("/artist/{artistId}/rehearsals/")
//    public ResponseEntity<List<RehearsalDto>> getArtistRehearsals(@PathVariable long artistId) {
//        return
//            ResponseEntity.ok(
//                service
//                    .getArtistRehearsals(artistId)
//                    .stream()
//                    .map(rehearsal -> rehearsal.toDto(config.getCanBeCancelledBefore()))
//                    .collect(toList())
//            );
//    }

//    @GetMapping("/rehearsal/{id}")
//    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
//        return ResponseEntity.of(service.get(id));
//    }
}
