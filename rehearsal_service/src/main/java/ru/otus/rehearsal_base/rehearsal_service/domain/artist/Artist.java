package ru.otus.rehearsal_base.rehearsal_service.domain.artist;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "artist")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private @NonNull String name;

    @Column(name = "genre")
    private String genre;

    @Column(name = "phone")
    private @NonNull String phone;

    @Column(name = "email")
    private String email;
}
