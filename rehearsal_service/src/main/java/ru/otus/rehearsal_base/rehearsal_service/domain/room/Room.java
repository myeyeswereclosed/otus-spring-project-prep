package ru.otus.rehearsal_base.rehearsal_service.domain.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomDto;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "room")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToOne
    @JoinColumn(name = "status_id")
    private RoomStatus status;

    @OneToOne
    @JoinColumn(name = "type_id")
    private RoomType roomType;

    @Column(name = "price")
    private int price;

    public LocalTime latestRehearsalTime() {
        return LocalTime.MIDNIGHT.minusHours(roomType.getRehearsalMinTime());
    }

    public boolean canBeReservedAt(LocalTime time) {
        return
            time.getMinute() == 0
                &&
            time.getSecond() == 0
                &&
            time.getNano() == 0
                &&
            time.getHour() % roomType.getRehearsalMinTime() == 0
        ;
    }
}
