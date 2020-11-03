package ru.otus.rehearsal_base.rehearsal_service.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.rehearsal_base.rehearsal_service.config.RehearsalConfig;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.Rehearsal;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.Room;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistDto;
import ru.otus.rehearsal_base.rehearsal_service.dto.RehearsalDto;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomDto;

@Component
@RequiredArgsConstructor
public class RehearsalMapper implements DtoMapper<Rehearsal, RehearsalDto> {
    private final DtoMapper<Artist, ArtistDto> artistMapper;
    private final DtoMapper<Room, RoomDto> roomMapper;
    private final RehearsalConfig config;

    @Override
    public Rehearsal toEntity(@NonNull RehearsalDto dto) {
        return
            new Rehearsal(
                dto.getId(),
                artistMapper.toEntity(dto.getArtist()),
                dto.getStartsAt(),
                roomMapper.toEntity(dto.getRoom()),
                dto.getRoom().getPrice()
            );
    }

    @Override
    public RehearsalDto toDto(@NonNull Rehearsal rehearsal) {
        var startDatetime = rehearsal.getStartDatetime();
        var rehearsalDuration = rehearsal.getRoom().getRoomType().getRehearsalMinTime();
        var status = rehearsal.getStatus();

        return
            new RehearsalDto(
                rehearsal.getId(),
                artistMapper.toDto(rehearsal.getArtist()),
                roomMapper.toDto(rehearsal.getRoom()),
                rehearsal.getStartDatetime(),
                startDatetime.toLocalDate(),
                startDatetime.toLocalTime(),
                startDatetime.toLocalTime().plusHours(rehearsalDuration),
                rehearsalDuration,
                rehearsal.getPrice(),
                rehearsal.getStatus(),
                rehearsal.getPaymentStatus(),
                !status.isCancelled() && rehearsal.canBeCancelled(config.getCanBeCancelledBefore())
            );
    }
}
