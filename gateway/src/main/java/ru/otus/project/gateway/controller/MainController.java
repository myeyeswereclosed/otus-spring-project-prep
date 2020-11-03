package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final ModelBuilder builder;

    @GetMapping("/")
    public String index(Model model) {
        builder.build(model);

        return "index";
    }
}
