package ru.otus.project.gateway.service.rest_client.rehearsal;

import org.springframework.stereotype.Component;
import ru.otus.project.gateway.model.rehearsal.Rehearsal;

import java.util.List;

import static java.util.Collections.emptyList;

@Component
public class DefaultRehearsalClient implements ReadRehearsalsClient {
    @Override
    public List<Rehearsal> reservedBy(String phone) {
        return emptyList();
    }
}
