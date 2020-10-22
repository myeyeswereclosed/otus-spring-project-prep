package ru.otus.project.prep.domain.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.project.prep.domain.artist.ArtistType;
import ru.otus.project.prep.dto.RoomDto;

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
    private ArtistType artistType;

    @Column(name = "price")
    private int price;

    public static Room fromDto(RoomDto dto) {
        return
            new Room(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                RoomStatus.fromDto(dto.getStatus()),
                ArtistType.fromDto(dto.getArtistType()),
                dto.getPrice()
            );
    }

    public RoomDto toDto() {
        return new RoomDto(id, name, description, status.toDto(), artistType.toDto(), price);
    }

    public LocalTime latestRehearsalTime() {
        return LocalTime.MIDNIGHT.minusHours(artistType.getRehearsalMinTime());
    }

    public boolean canBeReservedAt(LocalTime time) {
        return
            time.getMinute() == 0
                &&
            time.getSecond() == 0
                &&
            time.getNano() == 0
                &&
            time.getHour() % artistType.getRehearsalMinTime() == 0
        ;
    }
}
