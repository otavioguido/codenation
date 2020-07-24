package projeto.pratico.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import projeto.pratico.data.exception.InvalidFieldException;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.repository.EventRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.baeldung.com/java-dao-pattern
 * https://www.baeldung.com/jsf-spring-boot-controller-service-dao
 */

@Component
public class EventDao implements Dao<Event> {
    
    @Autowired
    private EventRepository eventRepository;

    private static final String ID = "id";
    private static final String LOG_LEVEL = "logLevel";
    private static final String EVENT_DESCRIPTION = "eventDescription";
    private static final String EVENT_LOG = "eventLog";
    private static final String ORIGIN = "origin";
    private static final String CREATED_AT = "createdAt";
    private static final String QUANTITY = "quantity";

    @Override
    public Optional<Event> get(UUID id) {
        return eventRepository.findById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll().stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void save(Event event) throws Exception {
        verifyEventField(event);
        if (event.getId() == null)
            eventRepository.saveAndFlush(event);
        else {
            Event eventDb = get(event.getId()).orElse(null);
            if (event.equals(eventDb)) {
                event.setQuantity(event.getQuantity() + 1);
                eventRepository.saveAndFlush(event);
            }
            throw new Exception();
        }
    }

    @Override
    public void update(Event event, Map<String, Object> params) throws Exception {
        for (Map.Entry<String, Object> param : params.entrySet()){
            switch (param.getKey()){
                case ID:
                case CREATED_AT:
                    throw new Exception();
                case LOG_LEVEL:
                    event.setLogLevel(String.valueOf(param.getValue()));
                    break;
                case EVENT_DESCRIPTION:
                    event.setEventDescription(String.valueOf(param.getValue()));
                    break;
                case EVENT_LOG:
                    event.setEventLog(String.valueOf(param.getValue()));
                    break;
                case ORIGIN:
                    event.setOrigin(String.valueOf(param.getValue()));
                    break;
                case QUANTITY:
                    event.setQuantity((Long) param.getValue());
                    break;
                default:
                    throw new Exception();
            }
        }

        eventRepository.saveAndFlush(event);
    }

    @Override
    public void delete(Event event) {
        eventRepository.delete(event);
    }

    private void verifyEventField(Event event){
        Arrays.stream(event.getClass().getFields()).forEach(field -> {
            try {
                stringNullorEmpty(field);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void stringNullorEmpty(Object object) throws Exception {
        if (object instanceof String)
            if (object == null)
                throw new Exception();
            if (((String) object).isEmpty()){}
                throw new Exception();

    }
}
