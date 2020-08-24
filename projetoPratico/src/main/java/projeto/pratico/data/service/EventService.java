package projeto.pratico.data.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projeto.pratico.data.exception.EventException;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.specification.EventSpec;

import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event saveEvent(Event event) throws EventException;

    Optional<Event> findById(UUID uuid);

    Page<Event> findAll(EventSpec eventSpec, Pageable pageRequest);

    void deleteEvent(Event event);

    Event updateEvent(Event event) throws EventException;
}
