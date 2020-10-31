package ru.otus.project.gateway.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomStatusDto {
    private String id;
    private String description;
}
