package ru.otus.project.gateway.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    private int id;
    private String name;
    private String description;
    private RoomStatusDto status;
    private RoomTypeDto roomType;
    private int price;
}
