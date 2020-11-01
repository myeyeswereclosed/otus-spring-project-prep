package ru.otus.project.gateway.service.room;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.dto.room.RoomDto;
import ru.otus.project.gateway.service.rest_client.RehearsalServiceClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RehearsalRoomService implements RoomService {
    private final RehearsalServiceClient client;

    @Override
    public List<RoomDto> getAll() {
        return client.activeRooms();
    }

    @Override
    public RoomDto roomInfo(int id) {
        return client.roomInfo(id);
    }
}
