package ru.otus.project.gateway.service.result;

import lombok.NonNull;

import java.util.Optional;

public class Failed<T> implements ServiceResult<T> {
    private final ServiceError error;

    public Failed(@NonNull ServiceError errorOccurred) {
        error = errorOccurred;
    }

    @Override
    public Optional<T> value() {
        return Optional.empty();
    }

    public Optional<ServiceError> error() {
        return Optional.of(error);
    }
}
