package ru.otus.rehearsal_base.rehearsal_service.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RoomDto {
    private final int id;
    private final String name;
    private final String description;
    private final RoomStatusDto status;
    private final RoomTypeDto roomType;
    private final int price;
}
