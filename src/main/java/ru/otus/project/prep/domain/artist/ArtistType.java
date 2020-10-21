package ru.otus.project.prep.domain.artist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
