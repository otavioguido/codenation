package projeto.pratico.data.service;

import projeto.pratico.data.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    Event saveEvent(Event event);

    Optional<Event> findById(UUID uuid);

    List<Event> findAll();

    void deleteEvent(Event event);

    Event updateEvent(Event event);
}
