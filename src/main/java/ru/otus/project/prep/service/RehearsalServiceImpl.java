package ru.otus.project.prep.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.project.prep.config.RehearsalConfig;
import ru.otus.project.prep.domain.rehearsal.Rehearsal;
import ru.otus.project.prep.repository.ArtistRepository;
import ru.otus.project.prep.repository.RehearsalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static ru.otus.project.prep.domain.rehearsal.RehearsalStatus.CANCELLED;

@Service
@RequiredArgsConstructor
public class RehearsalServiceImpl implements RehearsalService {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalServiceImpl.class);

    private final RehearsalConfig config;
    private final RehearsalRepository repository;
    private final ArtistRepository artistRepository;

    @Override
    public Rehearsal reserve(Rehearsal rehearsal) {
        return repository.saveAndFlush(rehearsal);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rehearsal> getReservedFromDate(int roomId, String date) {
        LocalDate fromDate = LocalDate.parse(date);
        LocalDate toDate = fromDate.plusDays(7);

        logger.info("Searching reserved rehearsals from {} to {}", date, toDate);

        return repository.findReserved(roomId, fromDate.atStartOfDay(), toDate.plusDays(1).atStartOfDay());
    }

    @Override
    @Transactional
    public Optional<Rehearsal> cancel(long id) throws TooLateToCancel {
        return repository.findById(id).map(this::cancel);
    }

    @Override
    public List<Rehearsal> getArtistRehearsals(long artistId) {
        return
            artistRepository
                .findById(artistId)
                .map(repository::findAllByArtist)
                .orElseGet(() -> {
                    logger.warn("Trying to find rehearsals, but artist with id {} not found", artistId);

                    return emptyList();
                });
    }

    @Override
    public Optional<Rehearsal> get(long rehearsalId) {
        return repository.findById(rehearsalId);
    }

    private Rehearsal cancel(Rehearsal rehearsal) throws TooLateToCancel {
        if (rehearsal.canBeCancelled(config.getCanBeCancelledBefore())) {
            return rehearsal.changeStatus(CANCELLED);
        }

        throw new TooLateToCancel(rehearsal);
    }
}
