package ru.otus.project.prep.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.prep.domain.room.Room;
import ru.otus.project.prep.domain.room.RoomStatus;
import ru.otus.project.prep.dto.RoomDto;
import ru.otus.project.prep.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomRepository repository;

    @GetMapping("/rooms")
    public List<RoomDto> getAllForArtist() {
        return
            repository
                .findAllByStatus(RoomStatus.active())
                .stream()
                .map(Room::toDto)
                .collect(toList())
            ;

    }

    @GetMapping("/room/{id}")
    public ResponseEntity<RoomDto> get(@PathVariable int id) {
        return
            ResponseEntity.of(
                repository
                    .findById(id)
                    .map(Room::toDto)
            );
    }
}
