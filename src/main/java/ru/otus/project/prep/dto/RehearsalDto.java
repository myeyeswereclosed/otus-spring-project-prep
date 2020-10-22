package ru.otus.project.prep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.otus.project.prep.domain.artist.Artist;
import ru.otus.project.prep.domain.rehearsal.PaymentStatus;
import ru.otus.project.prep.domain.rehearsal.Rehearsal;
import ru.otus.project.prep.domain.rehearsal.RehearsalStatus;
import ru.otus.project.prep.domain.room.Room;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RehearsalDto {
    private long id;
    private ArtistDto artist;
    private RoomDto room;
    /** TODO datetime **/
    private LocalDateTime startsAt;
    private int duration;
    private RehearsalStatus status;
    private PaymentStatus paymentStatus;
}
