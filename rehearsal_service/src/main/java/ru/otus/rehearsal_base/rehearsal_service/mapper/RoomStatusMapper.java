package ru.otus.rehearsal_base.rehearsal_service.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomStatus;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomStatusDto;

@Component
@RequiredArgsConstructor
public class RoomStatusMapper implements DtoMapper<RoomStatus, RoomStatusDto> {
    private final ModelMapper mapper;

    @Override
    public RoomStatus toEntity(@NonNull RoomStatusDto dto) {
        return mapper.map(dto, RoomStatus.class);
    }

    @Override
    public RoomStatusDto toDto(@NonNull RoomStatus status) {
        return mapper.map(status, RoomStatusDto.class);
    }
}
