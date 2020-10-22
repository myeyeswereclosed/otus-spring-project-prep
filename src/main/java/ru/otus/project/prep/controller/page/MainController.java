package ru.otus.project.prep.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.project.prep.domain.artist.Artist;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }
}
