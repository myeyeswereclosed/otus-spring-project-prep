package ru.otus.project.prep.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.project.prep.repository.ArtistRepository;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final ArtistRepository artistRepository;

    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/rooms/view")
    public String rooms(Model model) {
        // stub
        var artist = artistRepository.findById(1L).get().toDto();

        return "rooms";
    }

    @GetMapping("/rehearsals")
    public String reservations(Model model) {
        // stub
        var artist = artistRepository.findById(1L).get().toDto();

        model.addAttribute("artist", artist);

        return "rehearsals";
    }
}
