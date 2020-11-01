package ru.otus.rehearsal_base.rehearsal_service.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.otus.rehearsal_base.rehearsal_service.domain.artist.Artist;
import ru.otus.rehearsal_base.rehearsal_service.dto.ArtistDto;

@Component
@RequiredArgsConstructor
public class ArtistMapper implements DtoMapper<Artist, ArtistDto> {
    private final ModelMapper mapper;

    @Override
    public Artist toEntity(ArtistDto dto) {
        return mapper.map(dto, Artist.class);
    }

    @Override
    public ArtistDto toDto(Artist artist) {
        return mapper.map(artist, ArtistDto.class);
    }
}
