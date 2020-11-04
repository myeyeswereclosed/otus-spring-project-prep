package ru.otus.project.gateway.model.rehearsal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.project.gateway.model.artist.Artist;
import ru.otus.project.gateway.model.room.Room;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Rehearsal {
    private long id;
    private Artist artist;
    private Room room;
    private LocalDateTime startsAt;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int duration;
    private int price;
    private RehearsalStatus status;
    private PaymentStatus paymentStatus;
    private boolean cancelable = true;
}
