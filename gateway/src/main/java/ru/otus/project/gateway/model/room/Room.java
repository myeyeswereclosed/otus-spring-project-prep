package ru.otus.project.gateway.model.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Room implements Comparable<Room> {
    private int id;
    private String name;
    private String description;
    private RoomStatus status;
    private RoomType roomType;
    private int price;

    @Override
    public int compareTo(Room room) {
        if (roomType.getRehearsalMinTime() >= room.getRoomType().getRehearsalMinTime()) {
            return id < room.getId() ? -1 : 1;
        }

        return  1;
    }
}
