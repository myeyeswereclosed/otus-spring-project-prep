package ru.otus.project.prep.domain.rehearsal;

import ru.otus.project.prep.domain.Gear;
import ru.otus.project.prep.domain.artist.Artist;
import ru.otus.project.prep.domain.room.Room;

import java.time.LocalDateTime;
import java.util.Set;

public class Rehearsal {
    private long id;
    private Artist artist;
    private LocalDateTime startDatetime;
    private Room room;
    private Set<Gear> rentedGears;
    private RehearsalStatus status;
    private PaymentStatus paymentStatus;
}
