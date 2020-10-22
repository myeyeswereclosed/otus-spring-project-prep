package ru.otus.project.prep.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.project.prep.domain.artist.ArtistType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private long id;
    private String name;
    private String genre;
    private String phone;
    private String email;
    /** TODO **/
    private String password;
}
