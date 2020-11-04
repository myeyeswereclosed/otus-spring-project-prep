package ru.otus.project.gateway.controller.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.project.gateway.controller.ModelBuilder;
import ru.otus.project.gateway.model.artist.ArtistUser;
import ru.otus.project.gateway.model.phone.SmsCode;
import ru.otus.project.gateway.model.security.AccessToken;
import ru.otus.project.gateway.model.security.UserLogin;
import ru.otus.project.gateway.service.user.InvalidCredentials;
import ru.otus.project.gateway.service.user.UserService;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final ModelBuilder builder;
    private final UserService userService;

    @GetMapping("/register")
    public String register(Model model) {
        builder.build(
            model
                .addAttribute("artist", new ArtistUser())
                .addAttribute("smsCode", new SmsCode())
        );

        return "user/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        builder.build(model.addAttribute("user", new UserLogin()));

        return "user/login";
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public ResponseEntity<AccessToken> login(UserLogin user) {
        logger.info("Trying to login {}", user);

        return
            userService
                .accessToken(user.getPhone(), user.getPassword())
                .map(
                    token -> {
                        logger.info("Successfully got access token for {}", user.getPhone());

                        return ok(token);
                    }
                )
                .orElseGet(
                    () -> {
                        logger.warn("No access token for {}", user.getPhone());

                        return notFound().build();
                    }
                );
    }
}
