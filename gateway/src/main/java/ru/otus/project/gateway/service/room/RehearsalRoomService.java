package ru.otus.project.gateway.service.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.model.room.Room;
import ru.otus.project.gateway.service.rest_client.room.RoomRestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RehearsalRoomService implements RoomService {
    private final RoomRestClient client;

    @Override
    public List<Room> getAll() {
        return client.activeRooms();
    }

    @Override
    public Room roomInfo(int id) {
        return client.roomInfo(id);
    }
}
