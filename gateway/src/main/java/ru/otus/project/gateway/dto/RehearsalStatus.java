package ru.otus.project.gateway.dto;

public enum RehearsalStatus {
    RESERVED, CANCELLED, FINISHED;

    public boolean isCancelled() {
        return this.equals(CANCELLED);
    }
}
