package ru.otus.project.prep.domain.rehearsal;

public enum RehearsalStatus {
    RESERVED, CANCELLED, FINISHED;

    public boolean isCancelled() {
        return this.equals(CANCELLED);
    }
}
