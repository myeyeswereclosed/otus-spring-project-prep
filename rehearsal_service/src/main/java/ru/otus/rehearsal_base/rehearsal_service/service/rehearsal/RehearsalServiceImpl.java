package ru.otus.rehearsal_base.rehearsal_service.service.rehearsal;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.rehearsal_base.rehearsal_service.config.RehearsalConfig;
import ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.Rehearsal;
import ru.otus.rehearsal_base.rehearsal_service.repository.ArtistRepository;
import ru.otus.rehearsal_base.rehearsal_service.repository.RehearsalRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static ru.otus.rehearsal_base.rehearsal_service.domain.rehearsal.RehearsalStatus.CANCELLED;

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
    public List<Rehearsal> getReserved(int roomId, String fromDate, String toDate) {
        LocalDate start = LocalDate.parse(fromDate);
        LocalDate end = LocalDate.parse(toDate);

        logger.info("Searching reserved rehearsals from {} to {}", start, end);

        return repository.findReserved(roomId, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
    }

    @Override
    @Transactional
    public Optional<Rehearsal> cancel(long id) throws TooLateToCancel {
        return repository.findById(id).map(this::cancel);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Rehearsal> getArtistRehearsals(String phone) {
        return
            artistRepository
                .findByPhone(phone)
                .map(repository::findAllByArtist)
                .orElseGet(() -> {
                    logger.warn("Trying to find rehearsals, but artist with phone {} not found", phone);

                    return emptyList();
                });
    }

    @Override
    @Transactional(readOnly = true)
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
