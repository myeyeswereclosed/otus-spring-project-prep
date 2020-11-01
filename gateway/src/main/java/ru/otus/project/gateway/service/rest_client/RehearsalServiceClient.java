package ru.otus.project.gateway.service.rest_client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.project.gateway.dto.artist.ArtistDto;
import ru.otus.project.gateway.dto.rehearsal.RehearsalDto;
import ru.otus.project.gateway.dto.room.RoomDto;

import java.util.List;

// TODO how to get path without hardcode
@FeignClient(name = "rehearsal-service", path = "/rehearsal-service")
public interface RehearsalServiceClient {
    @GetMapping("/rooms/active")
    List<RoomDto> activeRooms();

    @GetMapping("/room/{id}")
    RoomDto roomInfo(@PathVariable int id);

    @PostMapping("/artist/{phone}/rehearsal")
    RehearsalDto reserve(@PathVariable String phone, @RequestBody RehearsalDto rehearsal);

    @GetMapping("/artist/{phone}/rehearsals")
    List<RehearsalDto> reservedBy(@PathVariable String phone);

    @GetMapping("/room/{roomId}/rehearsals/reserved/{fromDate}/{toDate}")
    List<RehearsalDto> reservedInPeriod(
        @PathVariable int roomId,
        @PathVariable String fromDate,
        @PathVariable String toDate
    );

    @DeleteMapping("/rehearsal/{id}")
    ResponseEntity<?> cancel(@PathVariable long id);

    @PostMapping("/artist")
    ArtistDto newArtist(ArtistDto artist);
}
