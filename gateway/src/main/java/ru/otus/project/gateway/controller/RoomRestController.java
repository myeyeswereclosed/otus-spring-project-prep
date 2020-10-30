package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.gateway.dto.rehearsal.RehearsalDto;
import ru.otus.project.gateway.dto.room.RoomDto;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
@RequiredArgsConstructor
public class RoomRestController {
    private static final Logger logger = LoggerFactory.getLogger(RoomRestController.class);

    private final RestTemplate restClient = new RestTemplate();
    private final String url = "http://localhost:8888/";

    @GetMapping("/rooms")
    public List<RoomDto> getAllForArtist() {
        return
            restClient
                .exchange(url + "rooms/active", GET, null, new ParameterizedTypeReference<List<RoomDto>>() {})
                .getBody()
        ;
    }

    @GetMapping("/room/{id}")
    public RoomDto get(@PathVariable int id) {
        logger.info("Getting info about room with id={}", id);

        return restClient.getForEntity(url + "room/" + id, RoomDto.class).getBody();
    }

    @GetMapping("/room/{roomId}/rehearsals/reserved/{fromDate}/{toDate}")
    public List<RehearsalDto> getReservedFromDate(
        @PathVariable int roomId,
        @PathVariable String fromDate,
        @PathVariable String toDate
    ) {
        return
            restClient
                .exchange(
                    url + "room/" + roomId + "/rehearsals/reserved/" + fromDate + "/" + toDate,
                    GET,
                    null,
                    new ParameterizedTypeReference<List<RehearsalDto>>() {}
                )
                .getBody()
            ;
    }


}
