package ru.otus.project.gateway.service.room;

import ru.otus.project.gateway.model.room.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();

    // TODO optional
    Room roomInfo(int id);
}
