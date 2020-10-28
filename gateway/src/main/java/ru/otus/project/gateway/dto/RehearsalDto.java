package ru.otus.project.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RehearsalDto {
    private long id;
    private ArtistDto artist;
    private RoomDto room;
    /** TODO datetime **/
    private LocalDateTime startsAt;
    private int duration;
    private RehearsalStatus status;
    private PaymentStatus paymentStatus;
    private boolean cancelable = true;
}
