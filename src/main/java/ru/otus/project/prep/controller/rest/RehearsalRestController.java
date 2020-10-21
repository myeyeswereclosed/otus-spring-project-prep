package ru.otus.project.prep.controller.rest;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.prep.domain.rehearsal.Rehearsal;
import ru.otus.project.prep.repository.ArtistRepository;
import ru.otus.project.prep.repository.RehearsalRepository;
import ru.otus.project.prep.service.RehearsalService;
import ru.otus.project.prep.service.RehearsalServiceImpl;
import ru.otus.project.prep.service.TooLateToCancel;

import java.util.List;

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
    public ResponseEntity<?> reserve(@RequestBody Rehearsal rehearsal) {
//        if (rehearsal.isValid())

        // stub
        var maybeArtist = artistRepository.findById(1L);

        return
            maybeArtist
                .map(
                    artist -> {
                        rehearsal.setArtist(artist);

                        return status(CREATED).body(service.reserve(rehearsal));
                    }
                )
                .orElse(status(INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/rehearsals/room/{roomId}/reserved/{date}")
    public ResponseEntity<List<Rehearsal>> getReservedFromDate(@PathVariable int roomId, @PathVariable String date) {
        return ResponseEntity.ok(service.getReservedFromDate(roomId, date));
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
    public ResponseEntity<List<Rehearsal>> getArtistRehearsals(@PathVariable long artistId) {
        return ResponseEntity.ok(service.getArtistRehearsals(artistId));
    }

    @GetMapping("/rehearsal/{id}")
    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
        return ResponseEntity.of(service.get(id));
    }
}
