package ru.otus.project.prep.service;

import ru.otus.project.prep.domain.rehearsal.Rehearsal;

import java.util.List;
import java.util.Optional;

public interface RehearsalService {
    Rehearsal reserve(Rehearsal rehearsal);

    List<Rehearsal> getReservedFromDate(int roomId, String date);

    Optional<Rehearsal> cancel(long rehearsalId) throws TooLateToCancel;

    List<Rehearsal> getArtistRehearsals(long artistId);

    Optional<Rehearsal> get(long rehearsalId);
}
