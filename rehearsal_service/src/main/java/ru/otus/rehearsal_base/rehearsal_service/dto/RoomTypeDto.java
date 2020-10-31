package ru.otus.rehearsal_base.rehearsal_service.dto;

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
