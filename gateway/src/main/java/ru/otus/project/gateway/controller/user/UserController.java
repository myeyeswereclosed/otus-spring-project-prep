package ru.otus.project.gateway.controller.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.gateway.config.AuthorizationConfig;
import ru.otus.project.gateway.dto.artist.ArtistDto;
import ru.otus.project.gateway.dto.artist.ArtistUserDto;
import ru.otus.project.gateway.dto.security.TokenResponseDto;
import ru.otus.project.gateway.dto.security.User;
import ru.otus.project.gateway.dto.security.UserLoginDto;
import ru.otus.project.gateway.service.artist.ArtistService;
import ru.otus.project.gateway.service.rehearsal.RehearsalService;
import ru.otus.project.gateway.service.user.UserAuthenticationService;
import ru.otus.project.gateway.service.user.UserService;

@RequiredArgsConstructor
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final AuthorizationConfig config;
    private final RestTemplate restClient = new RestTemplate();
    private final String authorizationServerUrl = "http://localhost:8090";
    private final String rehearsalServiceUrl = "http://localhost:8888";

    private final UserAuthenticationService service;
    private final UserService userService;
    private final ArtistService artistService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("artist", new ArtistUserDto());

        return "user/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new UserLoginDto());

        return "user/login";
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public TokenResponseDto login(UserLoginDto user) {
        logger.info("Trying to login {}", user);

        var authorizationResponse =
            userService.accessToken(user.getPhone(), user.getPassword());
//            restClient.postForEntity(
//                config.getTokenUri(),
//                new HttpEntity<>(formDataForTokenRequest(user), headersForTokenRequest()),
//                TokenResponseDto.class
//            );

        logger.info(
            "Authorization server responded:{} - {}", authorizationResponse, authorizationResponse
//            authorizationResponse.getStatusCode(),
//            authorizationResponse.getBody()
        );

//        return authorizationResponse.getBody();
        return authorizationResponse;

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

    private HttpHeaders headersForTokenRequest() {
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(config.getClientId(), config.getClientSecret());

        return headers;
    }

    private MultiValueMap<String, String> formDataForTokenRequest(UserLoginDto user) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();

        formData.add("username", user.getPhone());
        formData.add("password", user.getPassword());
        formData.add("client_id", config.getClientId());
        formData.add("grant_type", "password");

        return formData;
    }

    @PostMapping("/register")
    public String register(ArtistUserDto user, Model model) {
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
