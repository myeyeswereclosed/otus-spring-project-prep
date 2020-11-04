package ru.otus.project.authorization_server.domain;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Table(name = "rehearsal_base_user")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private @NonNull String name;

    @Column(name = "phone")
    private @NonNull String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @ToString.Exclude
    private @NonNull String password;

    @Column(name = "role")
    private @NonNull String role;
}
