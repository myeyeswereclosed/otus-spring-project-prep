package ru.otus.project.prep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.otus.project.prep.domain.artist.Artist;
import ru.otus.project.prep.domain.room.Room;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class RehearsalDto {
    private final long id;
    private final Artist artist;
    private final Room room;
    private final String startsAt;
    private final int duration;
//    private final String status;
//    private final String paymentStatus;
}
