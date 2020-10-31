package ru.otus.project.gateway.service.artist;

import ru.otus.project.gateway.dto.artist.ArtistDto;

public interface ArtistService {
    ArtistDto create(ArtistDto artist);
}
