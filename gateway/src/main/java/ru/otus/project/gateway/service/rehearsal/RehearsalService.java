package ru.otus.project.gateway.service.rehearsal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.otus.project.gateway.dto.rehearsal.RehearsalDto;

import java.util.List;

public interface RehearsalService {
    RehearsalDto reserve(String artistPhone, RehearsalDto rehearsal);

    List<RehearsalDto> reservedInPeriod(int roomId, String fromDate, String toDate);

    List<RehearsalDto> reservedByArtistWithPhone(String artistPhone);

    ResponseEntity<?> cancel(long rehearsalId);
}
