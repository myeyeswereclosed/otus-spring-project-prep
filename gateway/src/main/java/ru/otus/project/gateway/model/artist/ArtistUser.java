package ru.otus.project.gateway.model.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArtistUser {
    private long id;
    private String name;
    private String genre;
    private String phone;
    private String email;
    @ToString.Exclude
    private String password;
    private String role = "user";
}
