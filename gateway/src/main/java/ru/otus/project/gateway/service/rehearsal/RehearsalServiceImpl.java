package ru.otus.project.gateway.service.rehearsal;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.model.rehearsal.Rehearsal;
import ru.otus.project.gateway.service.rest_client.rehearsal.ReadRehearsalsClient;
import ru.otus.project.gateway.service.rest_client.rehearsal.RehearsalServiceClient;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RehearsalServiceImpl implements RehearsalService {
    private static final Logger logger = LoggerFactory.getLogger(RehearsalServiceImpl.class);

    private final RehearsalServiceClient client;
    private final ReadRehearsalsClient artistRehearsalsClient;

    @Override
    public Optional<Rehearsal> reserve(String artistPhone, Rehearsal rehearsal) {
        try {
            return Optional.ofNullable(client.reserve(artistPhone, rehearsal));
        } catch (Exception e) {
            logger.error("Exception {} occurred. Trace:\r\n{}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        }

        return Optional.empty();
    }

//    @Override
//    public List<RehearsalDto> reservedInPeriod(int roomId, String fromDate, String toDate) {
//        return client.reservedInPeriod(roomId, fromDate, toDate);
//    }

    @Override
    public List<Rehearsal> reservedBy(String artistPhone) {
        return artistRehearsalsClient.reservedBy(artistPhone);
    }

    @Override
    public Optional<Rehearsal> cancel(long rehearsalId) {
        try {
            return Optional.ofNullable(client.cancel(rehearsalId));
        } catch (Exception e) {
            logger.error("Exception {} occurred. Trace:\r\n{}", e.getMessage(), ExceptionUtils.getStackTrace(e));
        }

        return Optional.empty();
    }
}
