package ru.otus.project.prep.domain.artist;

import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "artist")
@NoArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "type_id")
    private ArtistType type;
}
