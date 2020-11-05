package ru.otus.rehearsal_base.rehearsal_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RoomDto implements Comparable<RoomDto> {
    private final int id;
    private final String name;
    private final String description;
    private final RoomStatusDto status;
    private final RoomTypeDto roomType;
    private final int price;

    @Override
    public int compareTo(RoomDto room) {
        if (roomType.getRehearsalMinTime() >= room.getRoomType().getRehearsalMinTime()) {
            return id < room.getId() ? -1 : 1;
        }

        return  1;
    }
}
