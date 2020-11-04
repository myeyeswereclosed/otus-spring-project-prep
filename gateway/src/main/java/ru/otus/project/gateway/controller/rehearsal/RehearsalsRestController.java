package ru.otus.project.gateway.controller.rehearsal;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.gateway.model.rehearsal.Rehearsal;
import ru.otus.project.gateway.model.security.User;
import ru.otus.project.gateway.service.rehearsal.RehearsalService;
import ru.otus.project.gateway.service.user.UserService;

import java.util.List;

import static org.apache.commons.lang3.exception.ExceptionUtils.getStackTrace;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RehearsalsRestController {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalsRestController.class);

    private final UserService userService;
    private final RehearsalService rehearsalService;

    @PostMapping("/rehearsal")
    public ResponseEntity<Rehearsal> reserve(@RequestBody Rehearsal rehearsal, Authentication authentication) {
        var response =
            userService
                .authorizedUser(authentication)
                .map(user -> reserve(user, rehearsal))
                .orElse(status(UNAUTHORIZED).build())
        ;

        logger.info("Response: {} {}", response.getStatusCode(), response.getBody());

        return response;
    }

    private ResponseEntity<Rehearsal> reserve(User user, Rehearsal rehearsal) {
        logger.info(
            "Trying to reserve rehearsal: room: {}, {}({} hour(s)) for {}({})",
            rehearsal.getRoom().getName(),
            rehearsal.getStartsAt(),
            rehearsal.getDuration(),
            user.getName(),
            user.getPhone()
        );

        return
            rehearsalService
                .reserve(user.getPhone(), rehearsal)
                .map(created -> status(CREATED).body(created))
                .orElse(status(INTERNAL_SERVER_ERROR).build())
            ;
    }

    @DeleteMapping("rehearsal/{id}")
    public ResponseEntity<Rehearsal> cancel(@PathVariable long id, Authentication authentication) {
        try {
            return
                userService
                    .authorizedUser(authentication)
                    .map(user -> cancel(id))
                    .orElse(status(UNAUTHORIZED).build())
            ;
        } catch (Exception e) {
            logger.info("Exception: {}, trace:\r\n{}", e.getMessage(), getStackTrace(e));

            return status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private ResponseEntity<Rehearsal> cancel(long id) {
        logger.info("Trying to cancel rehearsal {}", id);

        return
            rehearsalService
                .cancel(id)
                .map(ResponseEntity::ok)
                .orElse(status(INTERNAL_SERVER_ERROR).build())
            ;
    }

    @GetMapping("/artist/rehearsals")
    public ResponseEntity<List<Rehearsal>> getArtistRehearsals(Authentication authentication) {
        return
            userService
                .authorizedUser(authentication)
                .map(user -> {
                        logger.info("Trying to obtain rehearsals for user {}", user);

                        return ok(rehearsalService.reservedBy(user.getPhone()));
                    })
                .orElse(status(UNAUTHORIZED).build())
        ;
    }

//    @GetMapping("/room/{roomId}/rehearsals/reserved/{fromDate}/{toDate}")
//    public List<RehearsalDto> reservedInPeriod(
//        @PathVariable int roomId,
//        @PathVariable String fromDate,
//        @PathVariable String toDate
//    ) {
//        return rehearsalService.reservedInPeriod(roomId, fromDate, toDate);
//    }

//    @GetMapping("/rehearsal/{id}")
//    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
//        return ResponseEntity.of(service.get(id));
//    }
}
