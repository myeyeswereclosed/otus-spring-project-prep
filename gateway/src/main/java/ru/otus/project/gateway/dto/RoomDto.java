package ru.otus.project.gateway.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RoomDto {
    private final int id;
    private final String name;
    private final String description;
    private final RoomStatusDto status;
    private final ArtistTypeDto artistType;
    private final int price;
}
