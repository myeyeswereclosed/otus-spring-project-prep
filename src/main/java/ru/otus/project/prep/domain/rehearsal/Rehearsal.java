package ru.otus.project.prep.domain.rehearsal;

import lombok.*;
import ru.otus.project.prep.domain.Gear;
import ru.otus.project.prep.domain.artist.Artist;
import ru.otus.project.prep.domain.room.Room;
import ru.otus.project.prep.dto.RehearsalDto;

import javax.persistence.*;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Entity
@Table(name = "rehearsal")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Rehearsal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @Setter
    private Artist artist;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @OneToOne
    @JoinColumn(name = "room_id")
    private Room room;

//    private Set<Gear> rentedGears;

    @Enumerated(EnumType.STRING)
    private RehearsalStatus status = RehearsalStatus.RESERVED;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;

    public Rehearsal(LocalDateTime startDatetime, Room room) {
        this.startDatetime = startDatetime;
        this.room = room;
    }

    public Rehearsal changeStatus(RehearsalStatus status) {
        this.status = status;

        return this;
    }

    public boolean isValid(LocalTime minTime, LocalTime maxTime) {
        return
            startDatetime.toLocalTime().isAfter(minTime)
                &&
            startDatetime.toLocalTime().isBefore(room.latestRehearsalTime())
                &&
            room.canBeReservedAt(startDatetime.toLocalTime())
        ;
    }

    public boolean canBeCancelled(int hours) {
        return ChronoUnit.HOURS.between(LocalDateTime.now(), startDatetime) > hours;
    }
}
