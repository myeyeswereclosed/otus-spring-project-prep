package ru.otus.project.gateway.service.rehearsal;

import ru.otus.project.gateway.model.rehearsal.Rehearsal;

import java.util.List;
import java.util.Optional;

public interface RehearsalService {
    Optional<Rehearsal> reserve(String artistPhone, Rehearsal rehearsal);

    List<Rehearsal> reservedBy(String artistPhone);

    Optional<Rehearsal> cancel(long rehearsalId);
}
