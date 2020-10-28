package ru.otus.rehearsal_base.rehearsal_service.domain.artist;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistDto;

import javax.persistence.*;

@Entity
@Table(name = "artist")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public static Artist fromDto(ArtistDto dto) {
        return
            new Artist(
                dto.getId(),
                dto.getName(),
                dto.getGenre(),
                dto.getPhone(),
                dto.getEmail(),
                dto.getPassword()
            );
    }

    public ArtistDto toDto() {
        return new ArtistDto(id, name, genre, phone, email, password);
    }
}
