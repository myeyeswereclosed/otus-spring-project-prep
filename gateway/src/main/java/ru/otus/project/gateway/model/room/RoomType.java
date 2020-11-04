package ru.otus.project.gateway.model.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomType {
    private int id;
    private String name;
    private int rehearsalMinTime;
}
