package ru.otus.project.gateway.controller.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.model.artist.Artist;
import ru.otus.project.gateway.model.artist.ArtistUser;
import ru.otus.project.gateway.model.security.User;
import ru.otus.project.gateway.service.artist.ArtistService;
import ru.otus.project.gateway.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;
    private final ArtistService artistService;

    @GetMapping("/currentUser")
    public ResponseEntity<User> currentUser(Authentication authentication) {
        return ResponseEntity.of(userService.authorizedUser(authentication));
    }

    @PostMapping("/register")
    public ResponseEntity<Artist> register(@RequestBody ArtistUser artist, Model model) {
        logger.info("Registering new artist {}", artist);

        // TODO JTA???
        var result =
            userService
                .register(user(artist))
                .flatMap(authServerUser -> {
                    logger.info(
                        "{} successfully registered in auth server. Registering as rehearsal service artist",
                        artist
                    );

                    return
                        artistService.create(
                            new Artist(artist.getName(), artist.getGenre(),artist.getPhone(), artist.getEmail())
                    );
                });

        return ResponseEntity.of(result);
    }

    private User user(ArtistUser artistUser) {
        return
            new User(
                artistUser.getName(),
                artistUser.getPhone(),
                artistUser.getEmail(),
                artistUser.getPassword(),
                artistUser.getRole()
            );
    }
}
