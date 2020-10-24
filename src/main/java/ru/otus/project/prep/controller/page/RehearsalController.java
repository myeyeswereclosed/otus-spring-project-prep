package ru.otus.project.prep.controller.page;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.project.prep.config.Timetable;
import ru.otus.project.prep.repository.ArtistRepository;

@Controller
@RequiredArgsConstructor
public class RehearsalController {
    private final ArtistRepository artistRepository;
    private final Timetable timetable;

    @GetMapping("/room/{id}/timetable")
    public String home(Model model, @PathVariable String id) {
        // stub
        var artist = artistRepository.findById(1L).get().toDto();

        var timetableStart = timetable.getStart();
        var timetableEnd = timetable.getEnd();

        model.addAttribute("artist", artist);
        model.addAttribute("roomId", id);
        model.addAttribute("timetableStart", timetableStart);
        model.addAttribute("timetableEnd", timetableEnd);
        model.addAttribute("timetablePeriod", timetable.getPeriod());

        return "timetable";
    }
}
