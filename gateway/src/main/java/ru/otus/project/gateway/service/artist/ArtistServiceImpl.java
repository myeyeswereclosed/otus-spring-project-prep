package ru.otus.project.gateway.service.artist;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.dto.artist.ArtistDto;
import ru.otus.project.gateway.service.RehearsalServiceClient;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private final RehearsalServiceClient client;

    @Override
    public ArtistDto create(ArtistDto artist) {
        return client.newArtist(artist);
    }
}
