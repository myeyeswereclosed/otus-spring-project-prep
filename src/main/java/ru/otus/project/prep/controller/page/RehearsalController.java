package ru.otus.project.prep.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.project.prep.domain.rehearsal.Rehearsal;
import ru.otus.project.prep.dto.RehearsalDto;
import ru.otus.project.prep.dto.RoomDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Controller
public class RehearsalController {

    @GetMapping("/room/{id}/timetable")
    public String home(Model model, @PathVariable String id) {
        model.addAttribute("rehearsal", new Rehearsal());
        model.addAttribute("roomId", id);

        return "timetable";
    }



//    // get all reservations made by band/musician
//    public List<BookingDto> getAll(Artist user);
//
//    public BookingDto get(String id);
//
//    public BookingDto cancel(String id);
//
//    // update booking details (only stuff, not datetime)
//    public BookingDto update(BookingDto booking);
}
