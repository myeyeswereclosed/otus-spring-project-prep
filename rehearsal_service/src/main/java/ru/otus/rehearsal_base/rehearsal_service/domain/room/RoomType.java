package ru.otus.rehearsal_base.rehearsal_service.domain.room;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomTypeDto;

import javax.persistence.*;

@Entity
@Table(name="room_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "rehearsal_min_time")
    private int rehearsalMinTime;
}
