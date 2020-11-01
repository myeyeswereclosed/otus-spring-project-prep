package ru.otus.rehearsal_base.rehearsal_service.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.Rehearsal;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistDto;
import ru.otus.rehearsal_base.rehearsal_service.dto.RehearsalDto;
import ru.otus.rehearsal_base.rehearsal_service.mapper.DtoMapper;
import ru.otus.rehearsal_base.rehearsal_service.repository.ArtistRepository;
import ru.otus.rehearsal_base.rehearsal_service.service.rehearsal.RehearsalService;
import ru.otus.rehearsal_base.rehearsal_service.service.rehearsal.TooLateToCancel;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class RehearsalController {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalController.class);

    private final ArtistRepository artistRepository;
    private final RehearsalService service;

    private final DtoMapper<Rehearsal, RehearsalDto> mapper;
    private final DtoMapper<Artist, ArtistDto> artistMapper;

    @PostMapping("/artist/{phone}/rehearsal")
    public ResponseEntity<?> reserve(@PathVariable String phone, @RequestBody RehearsalDto rehearsal) {
        logger.info("Trying to reserve {} for {}", rehearsal, phone);

        return
            artistRepository
                .findByPhone(phone)
                .map(
                    artist ->
                        status(CREATED)
                            .body(
                                service.reserve(mapper.toEntity(rehearsal.setArtist(artistMapper.toDto(artist))))
                            )
                )
                .orElse(notFound().build());
    }

    @GetMapping("/room/{roomId}/rehearsals/reserved/{fromDate}/{toDate}")
    public ResponseEntity<List<RehearsalDto>> getReservedFromDate(
        @PathVariable int roomId,
        @PathVariable String fromDate,
        @PathVariable String toDate
    ) {
        return
            ResponseEntity.ok(
                service
                    .getReserved(roomId, fromDate, toDate)
                    .stream()
                    .map(mapper::toDto)
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

    @GetMapping("/artist/{phone}/rehearsals")
    public ResponseEntity<List<RehearsalDto>> getArtistRehearsals(@PathVariable String phone) {
        return
            ResponseEntity.ok(
                service
                    .getArtistRehearsals(phone)
                    .stream()
                    .map(mapper::toDto)
                    .collect(toList())
            );
    }

    @GetMapping("/rehearsal/{id}")
    public ResponseEntity<Rehearsal> getRehearsal(@PathVariable long id) {
        return ResponseEntity.of(service.get(id));
    }
}
