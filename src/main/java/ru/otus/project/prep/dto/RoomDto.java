package ru.otus.project.prep.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class RoomDto {
    private final long id;
    private final String name;
    private final String description;
    private final String status;
    private final String artistType;
    private final int price;
}
