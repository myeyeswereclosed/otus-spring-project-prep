package ru.otus.project.gateway.service.result;

import java.util.Optional;

public class Executed<T> implements ServiceResult<T> {
    private final T value;

    public Executed(T value) {
        this.value = value;
    }

    @Override
    public Optional<T> value() {
        return Optional.ofNullable(value);
    }

    @Override
    public Optional<ServiceError> error() {
        return Optional.empty();
    }
}
