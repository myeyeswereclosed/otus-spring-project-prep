package ru.otus.project.prep.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoomStatusDto {
    private final String id;
    private final String description;
}
