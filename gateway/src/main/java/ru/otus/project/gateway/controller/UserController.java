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
import ru.otus.project.gateway.dto.ArtistDto;
import ru.otus.project.gateway.dto.RehearsalBaseUserDto;
import ru.otus.project.gateway.dto.TokenResponseDto;
import ru.otus.project.gateway.dto.UserDto;

@RequiredArgsConstructor
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthorizationConfig config;
    private final RestTemplate restClient = new RestTemplate();
    private final PasswordEncoder encoder;
    private final String authorizationServerUrl = "http://localhost:8090";

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("artist", new ArtistDto());

        return "user/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        System.out.println("HERE");

        model
//            .addAttribute("tokenUri", config.getTokenUri())
//            .addAttribute("clientId", "any_id")
//            .addAttribute("clientSecret", "any_secret")
            .addAttribute("user", new UserDto())
        ;

        System.out.println("LOGIN");

        return "user/login";
    }

    @PostMapping("/clientLogin")
    @ResponseBody
    public TokenResponseDto login(UserDto user) {
        logger.info("Trying to login {}", user);

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(config.getClientId(), config.getClientSecret());

        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();

        formData.add("username", user.getUsername());
        formData.add("password", user.getPassword());
        formData.add("client_id", config.getClientId());
        formData.add("grant_type", "password");

        var response =
            restClient.postForEntity(
                config.getTokenUri(),
                new HttpEntity<>(formData, headers),
                TokenResponseDto.class
            );

        logger.info("Response is {}", response);

        return response.getBody();
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(ArtistDto user) {
        var response =
            restClient.postForEntity(
                authorizationServerUrl + "/register",
                new RehearsalBaseUserDto(
                    0,
                    user.getName(),
                    user.getPhone(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getRole()
                ),
                RehearsalBaseUserDto.class
            );

        logger.info("Response is {}", response);

        return "REGISTERED";
    }
}
