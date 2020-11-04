package ru.otus.project.gateway.controller.user;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.otus.project.gateway.controller.ModelBuilder;
import ru.otus.project.gateway.dto.artist.ArtistUserDto;
import ru.otus.project.gateway.dto.phone.SmsCodeDto;
import ru.otus.project.gateway.dto.security.TokenResponseDto;
import ru.otus.project.gateway.dto.security.UserLoginDto;
import ru.otus.project.gateway.service.user.UserService;

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
                .addAttribute("artist", new ArtistUserDto())
                .addAttribute("smsCode", new SmsCodeDto())
        );

        return "user/register";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        builder.build(model.addAttribute("user", new UserLoginDto()));

        return "user/login";
    }

    @PostMapping("/userLogin")
    @ResponseBody
    public TokenResponseDto login(UserLoginDto user) {
        logger.info("Trying to login {}", user);

        var authorizationResponse = userService.accessToken(user.getPhone(), user.getPassword());

        logger.info("Authorization server responded: {}", authorizationResponse);

        return authorizationResponse;
    }
}
