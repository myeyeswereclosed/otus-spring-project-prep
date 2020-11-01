package ru.otus.rehearsal_base.rehearsal_service.mapper;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomType;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomTypeDto;

@Component
@RequiredArgsConstructor
public class RoomTypeMapper implements DtoMapper<RoomType, RoomTypeDto> {
    private final ModelMapper mapper;

    @Override
    public RoomType toEntity(@NonNull RoomTypeDto dto) {
        return mapper.map(dto, RoomType.class);
    }

    @Override
    public RoomTypeDto toDto(@NonNull RoomType roomType) {
        return mapper.map(roomType, RoomTypeDto.class);
    }
}
