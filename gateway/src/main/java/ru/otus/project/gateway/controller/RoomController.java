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
        model
            .addAttribute("roomId", id)
            .addAttribute("timetableStart", timetable.getStart())
            .addAttribute("timetableEnd", timetable.getEnd())
            .addAttribute("timetablePeriod", timetable.getPeriod())
        ;

        return "timetable";
    }
}
