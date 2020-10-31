package ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal;

import lombok.*;
import lombok.experimental.Accessors;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.Room;
import ru.otus.rehearsal_base.rehearsal_service.dto.RehearsalDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "rehearsal")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Accessors(chain = true)
public class Rehearsal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToOne
    @Setter
    private @NonNull Artist artist;

    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @OneToOne
    @JoinColumn(name = "room_id")
    private @NonNull Room room;

    @Column(name = "price")
    private int price;

//    private Set<Gear> rentedGears;

    @Enumerated(EnumType.STRING)
    private RehearsalStatus status = RehearsalStatus.RESERVED;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.NOT_PAID;

    public Rehearsal(long id, Artist artist, LocalDateTime startDatetime, Room room) {
        this.id = id;
        this.artist = artist;
        this.startDatetime = startDatetime;
        this.room = room;
        this.price = room.getPrice();
    }

    public Rehearsal changeStatus(RehearsalStatus status) {
        this.status = status;

        return this;
    }

    public static Rehearsal fromDto(RehearsalDto dto) {
        return
            new Rehearsal(
                dto.getId(),
                Artist.fromDto(dto.getArtist()),
                dto.getStartsAt(),
                Room.fromDto(dto.getRoom())
            );
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

    public RehearsalDto toDto(int hoursToCancel) {
        return
            new RehearsalDto(
                id,
                artist.toDto(),
                room.toDto(),
                startDatetime,
                startDatetime.toLocalDate(),
                startDatetime.toLocalTime(),
                startDatetime.toLocalTime().plusHours(room.getArtistType().getRehearsalMinTime()),
                room.getArtistType().getRehearsalMinTime(),
                price,
                status,
                paymentStatus,
                !status.isCancelled() && canBeCancelled(hoursToCancel)
            );
    }
}
