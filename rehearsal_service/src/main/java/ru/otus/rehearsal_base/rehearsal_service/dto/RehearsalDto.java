package ru.otus.rehearsal_base.rehearsal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.PaymentStatus;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.RehearsalStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RehearsalDto {
    private long id;
    private ArtistDto artist;
    private RoomDto room;
    private LocalDateTime startsAt;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;
    private int duration;
    private int price;
    private RehearsalStatus status;
    private PaymentStatus paymentStatus;
    private boolean cancelable = true;
}
