package ru.otus.rehearsal_base.rehearsal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.PaymentStatus;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.RehearsalStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RehearsalDto {
    private long id;
    private ArtistDto artist;
    private RoomDto room;
    private LocalDateTime startsAt;
    private int duration;
    private RehearsalStatus status;
    private PaymentStatus paymentStatus;
    private boolean cancelable = true;
}
