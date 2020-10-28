package ru.otus.project.authorization_server.service.registration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.authorization_server.model.User;
import ru.otus.project.authorization_server.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserRegistrationService implements RegistrationService {
    private final UserRepository repository;

    @Override
    @Transactional
    public User register(User newUser) {
        return repository.save(newUser);
    }
}
