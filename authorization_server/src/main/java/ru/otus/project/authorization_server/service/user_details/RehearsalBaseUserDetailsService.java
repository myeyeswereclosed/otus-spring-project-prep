package ru.otus.project.authorization_server.service.user_details;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.project.authorization_server.domain.RehearsalBaseUser;
import ru.otus.project.authorization_server.repository.UserRepository;

@Service
@Primary
@RequiredArgsConstructor
public class RehearsalBaseUserDetailsService implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalBaseUserDetailsService.class);

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        logger.info("Trying to find user by phone {}", phone);

        return
            repository
                .findUserByPhone(phone)
                .map(user -> {
                    logger.info("Found user: {}", user);

                    return new RehearsalBaseUser(user);
                })
                .orElseThrow(
                    () -> new UsernameNotFoundException(String.format("User with phone %s not found", phone))
                );
    }
}
