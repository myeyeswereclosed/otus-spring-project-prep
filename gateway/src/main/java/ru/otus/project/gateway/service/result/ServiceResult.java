package ru.otus.project.gateway.service.result;

import java.util.List;
import java.util.Optional;

public interface ServiceResult<T> {
    Optional<T> value();

    Optional<ServiceError> error();

    default boolean isOk() {
        return error().isEmpty();
    }
}
