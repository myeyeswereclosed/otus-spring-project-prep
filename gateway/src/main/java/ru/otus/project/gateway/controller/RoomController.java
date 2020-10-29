package ru.otus.project.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.project.gateway.config.Timetable;


@Controller
@RequiredArgsConstructor
public class RoomController {
    private final Timetable timetable;

    @GetMapping("/room/{id}/timetable")
    public String roomTimetable(Model model, @PathVariable String id) {
        var timetableStart = timetable.getStart();
        var timetableEnd = timetable.getEnd();

//        model.addAttribute("artist", artist);
        model.addAttribute("roomId", id);
        model.addAttribute("timetableStart", timetableStart);
        model.addAttribute("timetableEnd", timetableEnd);
        model.addAttribute("timetablePeriod", timetable.getPeriod());

        return "timetable";
    }
}
