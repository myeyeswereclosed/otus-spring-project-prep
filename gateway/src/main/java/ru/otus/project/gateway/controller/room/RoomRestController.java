package ru.otus.project.gateway.controller.room;

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
import ru.otus.project.gateway.service.room.RoomService;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@RestController
@RequiredArgsConstructor
public class RoomRestController {
    private static final Logger logger = LoggerFactory.getLogger(RoomRestController.class);

//    private final RestTemplate restClient = new RestTemplate();
//    private final String url = "http://localhost:8888/";

    private final RoomService service;

    @GetMapping("/rooms")
    public List<RoomDto> getAllForArtist() {
        logger.info("Trying to obtain rooms info");

        return service.getAll();

//        return
//            restClient
//                .exchange(url + "rooms/active", GET, null, new ParameterizedTypeReference<List<RoomDto>>() {})
//                .getBody()
//        ;
    }

    @GetMapping("/room/{id}")
    public RoomDto get(@PathVariable int id) {
        logger.info("Getting info about room with id={}", id);

        return service.roomInfo(id);

//        return restClient.getForEntity(url + "room/" + id, RoomDto.class).getBody();
    }
}
