package ru.otus.project.gateway.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoomStatusDto {
    private final String id;
    private final String description;
}
