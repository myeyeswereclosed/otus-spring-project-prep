package ru.otus.project.gateway.service.artist;

import ru.otus.project.gateway.model.artist.Artist;

import java.util.Optional;

public interface ArtistService {
    Optional<Artist> create(Artist artist);
}
