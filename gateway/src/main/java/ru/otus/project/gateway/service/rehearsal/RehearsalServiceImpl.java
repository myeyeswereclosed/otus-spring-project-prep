package ru.otus.project.gateway.service.rehearsal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.otus.project.gateway.dto.rehearsal.RehearsalDto;
import ru.otus.project.gateway.service.rest_client.RehearsalServiceClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RehearsalServiceImpl implements RehearsalService {
    private final RehearsalServiceClient client;

    @Override
    public RehearsalDto reserve(String artistPhone, RehearsalDto rehearsal) {
        return client.reserve(artistPhone, rehearsal);
    }

//    @Override
//    public List<RehearsalDto> reservedInPeriod(int roomId, String fromDate, String toDate) {
//        return client.reservedInPeriod(roomId, fromDate, toDate);
//    }

    @Override
    public List<RehearsalDto> reservedByArtistWithPhone(String artistPhone) {
        return client.reservedBy(artistPhone);
    }

    @Override
    public ResponseEntity<?> cancel(long rehearsalId) {
        return client.cancel(rehearsalId);
    }
}
