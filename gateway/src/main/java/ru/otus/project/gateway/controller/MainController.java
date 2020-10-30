package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.otus.project.gateway.config.AuthorizationConfig;

@RequiredArgsConstructor
@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    private final AuthorizationConfig config;
    private final RestTemplate restClient = new RestTemplate();

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/rehearsals")
    public String rehearsals(Model model) {
        System.out.println("OLOLO");
        return "rehearsals";
    }

    @GetMapping("/rooms/view")
    public String rooms(Model model) {
        return "rooms";
    }
}
