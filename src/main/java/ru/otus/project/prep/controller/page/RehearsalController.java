package ru.otus.project.prep.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.project.prep.repository.ArtistRepository;

@Controller
@RequiredArgsConstructor
public class RehearsalController {
    private final ArtistRepository artistRepository;

    @GetMapping("/room/{id}/timetable")
    public String home(Model model, @PathVariable String id) {
        // stub
        var artist = artistRepository.findById(1L).get().toDto();

        model.addAttribute("artist", artist);
        model.addAttribute("roomId", id);

        return "timetable";
    }
}
