package ru.otus.rehearsal_base.rehearsal_service.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.Room;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomStatus;
import ru.otus.rehearsal_base.rehearsal_service.domain.room.RoomType;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomDto;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomStatusDto;
import ru.otus.rehearsal_base.rehearsal_service.dto.RoomTypeDto;

@Component
@RequiredArgsConstructor
public class RoomMapper implements DtoMapper<Room, RoomDto> {
    private final DtoMapper<RoomType, RoomTypeDto> typeMapper;
    private final DtoMapper<RoomStatus, RoomStatusDto> statusMapper;

    @Override
    public Room toEntity(RoomDto dto) {
        return
            new Room(
                dto.getId(),
                dto.getName(),
                dto.getDescription(),
                statusMapper.toEntity(dto.getStatus()),
                typeMapper.toEntity(dto.getRoomType()),
                dto.getPrice()
            );
    }

    @Override
    public RoomDto toDto(Room room) {
        return
            new RoomDto(
                room.getId(),
                room.getName(),
                room.getDescription(),
                statusMapper.toDto(room.getStatus()),
                typeMapper.toDto(room.getRoomType()),
                room.getPrice()
            );
    }
}
