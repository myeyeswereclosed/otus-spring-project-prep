package ru.otus.rehearsal_base.rehearsal_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.Room;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomStatus;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomDto;
import ru.otus.rehearsal_base.rehearsal_service.repository.RoomRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class RoomRestController {
    private final RoomRepository repository;

    @GetMapping("/rooms/active")
    public List<RoomDto> getActive() {
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
