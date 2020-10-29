package ru.otus.project.gateway.dto.rehearsal;

public enum RehearsalStatus {
    RESERVED, CANCELLED, FINISHED;

    public boolean isCancelled() {
        return this.equals(CANCELLED);
    }
}
