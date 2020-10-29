package ru.otus.project.gateway.dto.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomTypeDto {
    private int id;
    private String name;
    private int rehearsalMinTime;
}
