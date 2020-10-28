package ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal;

public enum RehearsalStatus {
    RESERVED, CANCELLED, FINISHED;

    public boolean isCancelled() {
        return this.equals(CANCELLED);
    }
}
