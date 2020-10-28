package ru.otus.project.gateway.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ArtistTypeDto {
    private final int id;
    private final String name;
    private final int rehearsalMinTime;
}
