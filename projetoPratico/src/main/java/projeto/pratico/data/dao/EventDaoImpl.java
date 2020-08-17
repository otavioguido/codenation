package projeto.pratico.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import projeto.pratico.data.exception.EventException;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.repository.EventRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * baeldung.com/rest-api-pagination-in-spring
 * https://www.baeldung.com/spring-data-jpa-pagination-sorting
 */

@Component
public class EventDaoImpl implements Dao<Event> {
    
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Optional<Event> get(UUID id) {
        return eventRepository.findById(id);
    }

    public List<Event> findByLogLevel(String logLevel, PageRequest pageRequest) {
        return eventRepository.findByLogLevel(logLevel, pageRequest);
    }

    public List<Event> findByEventDescription(String eventDescription, PageRequest pageRequest) {
        return eventRepository.findByEventDescription(eventDescription, pageRequest);
    }

    public List<Event> findByEventLog(String eventLog, PageRequest pageRequest) {
        return eventRepository.findByEventLog(eventLog, pageRequest);
    }

    public List<Event> findByOrigin(String origin, PageRequest pageRequest) {
        return eventRepository.findByOrigin(origin, pageRequest);
    }

    public List<Event> findByCreatedAt(String createdAt, PageRequest pageRequest) {
        return eventRepository.findByCreatedAt(createdAt, pageRequest);
    }

    public List<Event> findByQuantity(String quantity, PageRequest pageRequest) {
        return eventRepository.findByQuantity(quantity, pageRequest);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Event save(Event event) {
        verifyEventField(event);

        if (event.getId() == null) {
            return eventRepository.saveAndFlush(event);
        }

        return this.update(event);
    }

    @Override
    public Event update(Event event) {
        try {
            checkIfEventExist(event);
            verifyEventField(event);
            event.setQuantity(event.getQuantity().doubleValue() + 1.0);
            return eventRepository.saveAndFlush(event);
        } catch (EventException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    private void verifyEventField(Event event){
        Arrays.stream(event.getClass().getFields()).forEach(field -> {
            field.setAccessible(true);
            try {
                if (field.get(event) == null){
                    throw new EventException("Field " + field.getName() + " is null from event " + event.getId());
                }
                if (field.getType().isAssignableFrom(String.class)){
                    if (((String) field.get(event)).isEmpty()){
                        throw new EventException("Field " + field.getName() + " is empty from event " + event.getId());
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (EventException e) {
                e.printStackTrace();
            }
        });
    }

    public void checkIfEventExist(Event event) throws EventException {
        Event eventDb = this.get(event.getId()).orElse(null);

        if (!event.equals(eventDb)){
            throw new EventException("Trying to update event " + event.getId() + " but this event does not exist on the database");
        }
    }
}
