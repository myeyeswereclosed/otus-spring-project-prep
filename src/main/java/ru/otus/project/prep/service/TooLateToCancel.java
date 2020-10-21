package ru.otus.project.prep.service;

import ru.otus.project.prep.domain.rehearsal.Rehearsal;

public class TooLateToCancel extends RuntimeException {
    public TooLateToCancel(Rehearsal rehearsal) {
        super("Too late to cancel rehearsal: " + rehearsal);
    }
}
