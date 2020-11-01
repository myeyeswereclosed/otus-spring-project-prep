package ru.otus.rehearsal_base.rehearsal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomStatusDto {
    private String id;
    private String description;
}
