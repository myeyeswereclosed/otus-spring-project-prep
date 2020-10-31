package ru.otus.project.gateway.service.room;

import ru.otus.project.gateway.dto.room.RoomDto;

import java.util.List;

public interface RoomService {
    List<RoomDto> getAll();

    // TODO optional
    RoomDto roomInfo(int id);
}
