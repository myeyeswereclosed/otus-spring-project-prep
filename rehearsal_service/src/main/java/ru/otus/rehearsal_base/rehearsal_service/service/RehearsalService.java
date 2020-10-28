package ru.otus.rehearsal_base.rehearsal_service.service;

import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.Rehearsal;

import java.util.List;
import java.util.Optional;

public interface RehearsalService {
    Rehearsal reserve(Rehearsal rehearsal);

    List<Rehearsal> getReserved(int roomId, String fromDate, String toDate);

    Optional<Rehearsal> cancel(long rehearsalId) throws TooLateToCancel;

    List<Rehearsal> getArtistRehearsals(long artistId);

    Optional<Rehearsal> get(long rehearsalId);
}
