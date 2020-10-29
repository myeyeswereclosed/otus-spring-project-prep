package ru.otus.project.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomStatusDto {
    private String id;
    private String description;
}
