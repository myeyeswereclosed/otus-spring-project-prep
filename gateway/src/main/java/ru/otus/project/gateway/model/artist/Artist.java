package ru.otus.project.gateway.model.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private long id;
    private String name;
    private String genre;
    private String phone;
    private String email;

    public Artist(String name, String genre, String phone, String email) {
        this(0, name, genre, phone, email);
    }
}
