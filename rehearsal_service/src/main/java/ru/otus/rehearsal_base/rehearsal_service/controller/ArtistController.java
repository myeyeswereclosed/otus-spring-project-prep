package ru.otus.rehearsal_base.rehearsal_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistDto;
import ru.otus.rehearsal_base.rehearsal_service.mapper.DtoMapper;
import ru.otus.rehearsal_base.rehearsal_service.service.artist.ArtistService;

@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService service;
    private final DtoMapper<Artist, ArtistDto> mapper;

    @PostMapping("/artist")
    public ResponseEntity<ArtistDto> add(@RequestBody ArtistDto artist) {
        return ResponseEntity.ok(mapper.toDto(service.add(mapper.toEntity(artist))));
    }
}
