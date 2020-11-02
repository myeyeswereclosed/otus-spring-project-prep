package ru.otus.project.gateway.controller.room;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.dto.room.RoomDto;
import ru.otus.project.gateway.service.room.RoomService;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class RoomRestController {
    private static final Logger logger = LoggerFactory.getLogger(RoomRestController.class);

    private final RoomService service;

    @GetMapping("/rooms")
    public List<RoomDto> getAllForArtist() {
        logger.info("Trying to obtain rooms info");

        return service.getAll().stream().sorted().collect(toList());
    }

    @GetMapping("/room/{id}")
    public RoomDto get(@PathVariable int id) {
        logger.info("Getting info about room with id={}", id);

        return service.roomInfo(id);
    }
}
