package ru.otus.project.prep.domain.room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.otus.project.prep.dto.RoomStatusDto;

import javax.persistence.*;

@Entity
@Table(name = "room_status")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "description")
    private String description;

    public static RoomStatus active() {
        return new RoomStatus("active");
    }

    public RoomStatus(String id) {
        this.id = id;
    }

    public static RoomStatus fromDto(RoomStatusDto dto) {
        return new RoomStatus(dto.getId(), dto.getDescription());
    }

    public RoomStatusDto toDto() {
        return new RoomStatusDto(id, description);
    }
}
