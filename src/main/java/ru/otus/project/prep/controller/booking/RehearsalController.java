package ru.otus.project.prep.controller.booking;

import ru.otus.project.prep.dto.RehearsalDto;
import ru.otus.project.prep.dto.RoomDto;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class RehearsalController {
    // get all reservations made for this week (from -> to) in some Room
    public List<RehearsalDto> getFree(RoomDto room, LocalDateTime fromDate, LocalDateTime toDate) {
        return Collections.emptyList();
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
