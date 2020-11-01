package ru.otus.rehearsal_base.rehearsal_service.mapper;

import lombok.NonNull;

public interface DtoMapper<E, D> {
    @NonNull E toEntity(@NonNull D dto);

    @NonNull D toDto(@NonNull E entity);
}
