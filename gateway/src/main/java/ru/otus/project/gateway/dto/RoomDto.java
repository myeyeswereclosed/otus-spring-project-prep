package ru.otus.project.gateway.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomDto {
    private int id;
    private String name;
    private String description;
    private RoomStatusDto status;
    private ArtistTypeDto artistType;
    private int price;
}
