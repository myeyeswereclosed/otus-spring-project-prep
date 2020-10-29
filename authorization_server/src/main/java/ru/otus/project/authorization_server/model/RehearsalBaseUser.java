package ru.otus.project.authorization_server.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@ToString
@Getter
public class RehearsalBaseUser extends User {
    private final long id;
    private final String phone;
    private final String email;

    public RehearsalBaseUser(
        ru.otus.project.authorization_server.model.User user
    ) {
        super(user.getName(), user.getPassword(), Set.of(new SimpleGrantedAuthority(user.getRole())));

        this.id = user.getId();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }
}
