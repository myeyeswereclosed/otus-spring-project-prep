package ru.otus.project.gateway.controller.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.project.gateway.dto.artist.ArtistDto;
import ru.otus.project.gateway.dto.artist.ArtistUserDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.dto.security.UserLoginDto;
import ru.otus.project.gateway.service.artist.ArtistService;
import ru.otus.project.gateway.service.user.UserAuthenticationService;
import ru.otus.project.gateway.service.user.UserService;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserAuthenticationService service;
    private final UserService userService;
    private final ArtistService artistService;

    @GetMapping("/checkAccess")
    public User checkAccess(Authentication authentication) {
        // TODO говнокод
        return service.authenticatedUser(authentication).orElse(null);
    }

    @PostMapping("/register")
    public String register(@RequestBody ArtistUserDto user, Model model) {
        System.out.println("OLOLOLOLO" + user);

        // TODO completable future??
        var authorizationServerResponse =
            userService.register(
                new User(
                    user.getName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
                )
            );
//            restClient.postForEntity(
//                authorizationServerUrl + "/register",
//                new User(
//                    user.getName(),
//                    user.getPhone(),
//                    user.getEmail(),
//                    user.getPassword(),
//                    user.getRole()
//                ),
//                User.class
//            );

        var rehearsalServiceResponse =
            artistService.create(
                new ArtistDto(user.getName(), user.getGenre(), user.getPhone(), user.getEmail())
            );
//            restClient.postForEntity(
//                rehearsalServiceUrl + "/artist",
//                new ArtistDto(user.getName(), user.getGenre(), user.getPhone(), user.getEmail()),
//                ArtistDto.class
//            );

        model.addAttribute("user", new UserLoginDto());

        logger.info("Response is {}", authorizationServerResponse);

        return "user/login";
    }
}
