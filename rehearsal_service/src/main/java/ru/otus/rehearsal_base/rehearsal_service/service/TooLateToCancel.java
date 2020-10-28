package ru.otus.rehearsal_base.rehearsal_service.service;

import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.Rehearsal;

public class TooLateToCancel extends RuntimeException {
    public TooLateToCancel(Rehearsal rehearsal) {
        super("Too late to cancel rehearsal: " + rehearsal);
    }
}
