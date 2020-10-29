package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.gateway.config.AuthorizationConfig;
import ru.otus.project.gateway.dto.artist.ArtistAccountDto;
import ru.otus.project.gateway.dto.artist.ArtistUserDto;
import ru.otus.project.gateway.dto.artist.ArtistDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.dto.security.TokenResponseDto;
import ru.otus.project.gateway.dto.security.UserLoginDto;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthorizationConfig config;
    private final RestTemplate restClient = new RestTemplate();
    private final String authorizationServerUrl = "http://localhost:8090";
    private final String rehearsalServiceUrl = "http://localhost:8888";

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("artist", new ArtistUserDto());

        return "user/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        System.out.println("HERE");

        model.addAttribute("user", new UserLoginDto());

        System.out.println("LOGIN");

        return "user/login";
    }

    @PostMapping("/clientLogin")
    @ResponseBody
    public TokenResponseDto login(UserLoginDto user) {
        logger.info("Trying to login {}", user);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(config.getClientId(), config.getClientSecret());

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        formData.add("username", user.getPhone());
        formData.add("password", user.getPassword());
        formData.add("client_id", config.getClientId());
        formData.add("grant_type", "password");

        // TODO completable future
        var authorizationResponse =
            restClient.postForEntity(
                config.getTokenUri(),
                new HttpEntity<>(formData, headers),
                TokenResponseDto.class
            );

        logger.info("Authorization server responded {}", authorizationResponse);

        return authorizationResponse.getBody();

//        var rehearsalServiceResponse =
//            restClient.getForEntity(
//                rehearsalServiceUrl + "/artist?phone=" + user.getPhone(),
//                ArtistDto.class
//            );
//
//        logger.info("Rehearsal service responded {}", rehearsalServiceResponse);
//
//        // TODO возможно на этом этапе не нужно тянуть данные из сервиса репетиций
//        return
//            ArtistAccountDto
//                .fromTokenAndPersonalData(
//                    Objects.requireNonNull(authorizationResponse.getBody()),
//                    Objects.requireNonNull(rehearsalServiceResponse.getBody())
//                );
    }

    @PostMapping("/register")
    public String register(ArtistUserDto user, Model model) {
        var authorizationServerResponse =
            restClient.postForEntity(
                authorizationServerUrl + "/register",
                new User(
                    user.getName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
                ),
                User.class
            );

        var rehearsalServiceResponse =
            restClient.postForEntity(
                rehearsalServiceUrl + "/artist",
                new ArtistDto(user.getName(), user.getGenre(), user.getPhone(), user.getEmail()),
                ArtistDto.class
            );

        model.addAttribute("user", new UserLoginDto());

        logger.info("Response is {}", authorizationServerResponse);

        return "user/login";
    }
}
