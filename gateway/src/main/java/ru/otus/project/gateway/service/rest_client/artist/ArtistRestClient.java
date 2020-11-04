package ru.otus.project.gateway.service.rest_client.artist;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.otus.project.gateway.model.artist.Artist;

@FeignClient(
    name = "rehearsal-service",
    path = "/rehearsal-service",
    contextId = "artist"
)
public interface ArtistRestClient {
    @PostMapping("/artist")
    Artist create(Artist artist);
}
