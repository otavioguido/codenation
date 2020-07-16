package projeto.pratico.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event saveEvent(Event event){
        return eventRepository.saveAndFlush(event);
    }

    @Override
    public Optional<Event> findById(UUID uuid){
        return eventRepository.findById(uuid);
    }

    @Override
    public List<Event> findAll(){
        return eventRepository.findAll();
    }

    @Override
    public void deleteEvent(Event event){
        eventRepository.delete(event);
    }
}
