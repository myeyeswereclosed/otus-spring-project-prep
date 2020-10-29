package ru.otus.project.gateway.dto.room;

import lombok.*;

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
