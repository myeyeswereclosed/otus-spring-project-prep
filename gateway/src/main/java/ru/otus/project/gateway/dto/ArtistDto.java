package ru.otus.project.gateway.dto;

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
    /** TODO **/
    private String password;
    private String role = "user";
}
