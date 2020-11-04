package ru.otus.project.gateway.service.rest_client.room;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.project.gateway.model.room.Room;

import java.util.List;

@FeignClient(
    name = "rehearsal-service",
    path = "/rehearsal-service",
    contextId = "rooms"
)
public interface RoomRestClient {
    @GetMapping("/rooms/active")
    List<Room> activeRooms();

    @GetMapping("/room/{id}")
    Room roomInfo(@PathVariable int id);
}
