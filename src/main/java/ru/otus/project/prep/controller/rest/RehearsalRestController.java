package ru.otus.project.prep.controller.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.prep.domain.rehearsal.Rehearsal;
import ru.otus.project.prep.dto.RehearsalDto;
import ru.otus.project.prep.repository.ArtistRepository;
import ru.otus.project.prep.repository.RehearsalRepository;
import ru.otus.project.prep.service.RehearsalService;
import ru.otus.project.prep.service.RehearsalServiceImpl;
import ru.otus.project.prep.service.TooLateToCancel;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RehearsalRestController {
    private static Logger logger = LoggerFactory.getLogger(RehearsalRestController.class);

    private final ArtistRepository artistRepository;
    private final RehearsalService service;

    @PostMapping("/rehearsal")
    public ResponseEntity<?> reserve(@RequestBody RehearsalDto rehearsal) {
//        if (rehearsal.isValid())

        // stub
        var maybeArtist = artistRepository.findById(1L);

        return
            maybeArtist
                .map(artist -> status(CREATED).body(service.reserve(Rehearsal.fromDto(rehearsal))))
                .orElse(status(INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/rehearsals/room/{roomId}/reserved/{date}")
    public ResponseEntity<List<RehearsalDto>> getReservedFromDate(
        @PathVariable int roomId,
        @PathVariable String date
    ) {
        return
            ResponseEntity.ok(
                service
                    .getReservedFromDate(roomId, date)
                    .stream()
                    .map(Rehearsal::toDto)
                    .collect(toList())
            );
    }

    @DeleteMapping("rehearsal/{id}")
    public ResponseEntity<?> cancel(@PathVariable long id) {
        try {
            return ResponseEntity.of(service.cancel(id));
        } catch (TooLateToCancel e) {
            return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/artist/{artistId}/rehearsals/")
    public ResponseEntity<List<RehearsalDto>> getArtistRehearsals(@PathVariable long artistId) {
        return
            ResponseEntity.ok(
                service
                    .getArtistRehearsals(artistId)
                    .stream()
                    .map(Rehearsal::toDto)
                    .collect(toList())
            );
    }

    @GetMapping("/rehearsal/{id}")
    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
        return ResponseEntity.of(service.get(id));
    }
}
