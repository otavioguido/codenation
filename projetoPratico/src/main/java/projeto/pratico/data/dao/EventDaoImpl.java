package projeto.pratico.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import projeto.pratico.data.exception.EventException;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.repository.EventRepository;
import projeto.pratico.data.specification.EventSpec;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Component
public class EventDaoImpl implements Dao<Event> {
    
    @Autowired
    private EventRepository eventRepository;

    @Override
    public Optional<Event> get(UUID id) {
        return eventRepository.findById(id);
    }

    @Override
    public Page<Event> getAll(EventSpec eventSpec, Pageable pageRequest) {
        return eventRepository.findAll(eventSpec, pageRequest);
    }

    @Override
    public Event save(Event event) throws EventException {
        verifyEventField(event);

        if (event.getId() == null) {
            return eventRepository.saveAndFlush(event);
        }

        return this.update(event);
    }

    @Override
    public Event update(Event event) throws EventException {
        Event eventDb = checkIfEventExist(event);
        verifyEventField(event);
        if (eventDb.equals(event)) {
            event.setQuantity(event.getQuantity().doubleValue() + 1.0);
        }
        return eventRepository.saveAndFlush(event);
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

    private Event checkIfEventExist(Event event) throws EventException {
        Event eventDb = this.get(event.getId()).orElse(null);

        if (eventDb == null){
            throw new EventException("Trying to update event " + event.getId() + " but this event does not exist on the database");
        }

        return eventDb;
    }
}
