package projeto.pratico.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projeto.pratico.data.exception.InvalidFieldException;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.repository.EventRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * https://www.baeldung.com/java-dao-pattern
 * https://www.baeldung.com/jsf-spring-boot-controller-service-dao
 */

@Component
public class EventDaoImpl {
    
    @Autowired
    private EventRepository eventRepository;

    
    public Event saveEvent(Event event) throws Exception {
        verifyEventField(event);
        if (event.getId() == null)
            return eventRepository.saveAndFlush(event);
        else {
            Event eventDb = findById(event.getId()).orElse(null);
            if (event.equals(eventDb)) {
                event.setQuantity(event.getQuantity().doubleValue() + 1.0);
                return eventRepository.saveAndFlush(event);
            }
            throw new Exception();
        }
    }

    
    public Optional<Event> findById(UUID uuid){
        return eventRepository.findById(uuid);
    }

    
    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    
    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }

    private void verifyEventField(Event event){
        Arrays.stream(event.getClass().getFields()).forEach(field -> {
            try {
                stringNullorEmpty(field);
            } catch (InvalidFieldException e) {
                e.printStackTrace();
            }
        });
    }

    private void stringNullorEmpty(Object object) throws InvalidFieldException {
        if (object == null)
            //throw new InvalidFieldException("Field " + object.getClass().getName() + " is null");
        if (object instanceof String)
            if (((String) object).isEmpty()){}
                //throw new InvalidFieldException("Field " + object.getClass().getName() + " is empty");

    }
}
