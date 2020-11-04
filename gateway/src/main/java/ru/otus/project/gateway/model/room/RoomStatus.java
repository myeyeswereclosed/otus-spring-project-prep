package ru.otus.project.gateway.model.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomStatus {
    private String id;
    private String description;
}
