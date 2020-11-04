package ru.otus.project.gateway.service.artist;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.model.artist.Artist;
import ru.otus.project.gateway.service.rest_client.artist.ArtistRestClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {
    private static final Logger logger = LoggerFactory.getLogger(ArtistServiceImpl.class);

    private final ArtistRestClient client;

    @Override
    public Optional<Artist> create(Artist artist) {
        try {
            return Optional.ofNullable(client.create(artist));
        } catch (Exception e) {
            logger.error("Exception {} occurred. Trace:\r\n {}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        }

        return Optional.empty();
    }
}
