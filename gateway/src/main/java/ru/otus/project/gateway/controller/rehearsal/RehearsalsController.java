package ru.otus.project.gateway.controller.rehearsal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class RehearsalsController {
    @GetMapping("/rehearsals")
    public String rehearsals() {
        return "rehearsals";
    }
}
