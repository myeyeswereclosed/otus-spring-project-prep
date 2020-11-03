package ru.otus.project.gateway.controller.rehearsal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.project.gateway.controller.ModelBuilder;

@RequiredArgsConstructor
@Controller
public class RehearsalsController {
    private final ModelBuilder builder;

    @GetMapping("/rehearsals")
    public String rehearsals(Model model) {
        builder.build(model);

        return "rehearsals";
    }
}
