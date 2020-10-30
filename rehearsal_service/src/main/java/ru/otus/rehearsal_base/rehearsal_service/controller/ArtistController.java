package ru.otus.rehearsal_base.rehearsal_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistDto;
import ru.otus.rehearsal_base.rehearsal_service.service.artist.ArtistService;

@RestController
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService service;

    @PostMapping("/artist")
    public ArtistDto add(@RequestBody ArtistDto artist) {
        return
            service
                .add(Artist.fromDto(artist))
                .toDto()
        ;
    }
}
