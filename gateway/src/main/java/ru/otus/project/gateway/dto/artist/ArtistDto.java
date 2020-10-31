package ru.otus.project.gateway.dto.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistDto {
    private long id;
    private String name;
    private String genre;
    private String phone;
    private String email;

    public ArtistDto(String name, String genre, String phone, String email) {
        this(0, name, genre, phone, email);
    }
}
