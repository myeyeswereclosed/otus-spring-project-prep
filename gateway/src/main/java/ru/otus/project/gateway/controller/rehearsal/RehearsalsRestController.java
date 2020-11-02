package ru.otus.project.gateway.controller.rehearsal;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.gateway.dto.rehearsal.RehearsalDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.service.rehearsal.RehearsalService;
import ru.otus.project.gateway.service.user.UserAuthenticationService;

import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RehearsalsRestController {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalsRestController.class);

    private final UserAuthenticationService userService;
    private final RehearsalService rehearsalService;

    @PostMapping("/rehearsal")
    public ResponseEntity<?> reserve(@RequestBody RehearsalDto rehearsal, Authentication authentication) {
        var response =
            userService
                .authenticatedUser(authentication)
                .map(user -> ResponseEntity.ok(reserve(user, rehearsal)))
                .orElse(ResponseEntity.notFound().build())
        ;

        logger.info("Response IS " + response);

        return response;
    }

    private RehearsalDto reserve(User user, RehearsalDto rehearsal) {
        logger.info(
            "Trying to reserve rehearsal: room: {}, {}({} hour(s)) for {}({})",
            rehearsal.getRoom().getName(),
            rehearsal.getStartsAt(),
            rehearsal.getDuration(),
            user.getName(),
            user.getPhone()
        );

        var response = rehearsalService.reserve(user.getPhone(), rehearsal);

        logger.info("Response from rehearsal service: {}", response);

        return response;
    }

    @DeleteMapping("rehearsal/{id}")
    public ResponseEntity<?> cancel(@PathVariable long id) {
        try {
            rehearsalService.cancel(id);

            return ok().build();
        } catch (Exception e) {
            logger.info("Exception: {}, trace:\r\n{}", e.getMessage(), getStackTrace(e));

            return status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("artist/rehearsals")
    public ResponseEntity<List<RehearsalDto>> getArtistRehearsals(Authentication authentication) {
        return
            ok(
                userService
                    .authenticatedUser(authentication)
                    .map(
                        user -> {
                            logger.info("Trying to obtain rehearsals for user {}", user);

                            return rehearsalService.reservedByArtistWithPhone(user.getPhone());
                        })
                    .orElse(Collections.emptyList())
            )
        ;
    }

    @GetMapping("room/{roomId}/rehearsals/reserved/{fromDate}/{toDate}")
    public List<RehearsalDto> reservedInPeriod(
        @PathVariable int roomId,
        @PathVariable String fromDate,
        @PathVariable String toDate
    ) {
        return rehearsalService.reservedInPeriod(roomId, fromDate, toDate);
    }

//    @GetMapping("/rehearsal/{id}")
//    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
//        return ResponseEntity.of(service.get(id));
//    }
}
