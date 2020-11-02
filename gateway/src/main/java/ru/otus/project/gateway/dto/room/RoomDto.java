package ru.otus.project.gateway.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto implements Comparable<RoomDto> {
    private int id;
    private String name;
    private String description;
    private RoomStatusDto status;
    private RoomTypeDto roomType;
    private int price;

    @Override
    public int compareTo(RoomDto room) {
        if (roomType.getRehearsalMinTime() >= room.getRoomType().getRehearsalMinTime()) {
            return id < room.getId() ? -1 : 1;
        }

        return  1;
    }
}
