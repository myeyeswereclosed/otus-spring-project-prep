package ru.otus.rehearsal_base.rehearsal_service.domain.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistTypeDto;

import javax.persistence.*;

@Entity
@Table(name="artist_type")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArtistType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "rehearsal_min_time")
    private int rehearsalMinTime;

    public static ArtistType fromDto(ArtistTypeDto dto) {
        return new ArtistType(dto.getId(), dto.getName(), dto.getRehearsalMinTime());
    }

    public ArtistTypeDto toDto() {
        return new ArtistTypeDto(id, name, rehearsalMinTime);
    }
}
