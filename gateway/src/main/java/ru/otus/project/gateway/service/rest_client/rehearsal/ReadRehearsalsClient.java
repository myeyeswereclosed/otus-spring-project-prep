package ru.otus.project.gateway.service.rest_client.rehearsal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.project.gateway.model.rehearsal.Rehearsal;

import java.util.List;

@FeignClient(
    name = "rehearsal-service",
    path = "/rehearsal-service",
    fallback = DefaultRehearsalClient.class,
    contextId = "readRehearsals"
)
public interface ReadRehearsalsClient {
    @GetMapping("/artist/{phone}/rehearsals")
    List<Rehearsal> reservedBy(@PathVariable String phone);

//    @GetMapping("/room/{roomId}/rehearsals/reserved/{fromDate}/{toDate}")
//    List<RehearsalDto> reservedInPeriod(
//        @PathVariable int roomId,
//        @PathVariable String fromDate,
//        @PathVariable String toDate
//    );
}
