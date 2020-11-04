package ru.otus.project.gateway.model.rehearsal;

public enum RehearsalStatus {
    RESERVED, CANCELLED, FINISHED;

    public boolean isCancelled() {
        return this.equals(CANCELLED);
    }
}
