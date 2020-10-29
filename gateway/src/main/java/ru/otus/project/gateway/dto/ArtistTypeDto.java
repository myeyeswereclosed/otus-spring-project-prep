package ru.otus.project.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ArtistTypeDto {
    private int id;
    private String name;
    private int rehearsalMinTime;
}
