package ru.otus.project.authorization_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.project.authorization_server.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByPhone(String phone);
}
