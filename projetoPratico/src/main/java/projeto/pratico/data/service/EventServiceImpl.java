package projeto.pratico.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.pratico.data.dao.Dao;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.repository.EventRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private Dao<Event> eventDao;

    @Override
    public Event saveEvent(Event event){
        return eventDao.save(event);
    }

    @Override
    public Optional<Event> findById(UUID uuid){
        return eventDao.get(uuid);
    }

    @Override
    public List<Event> findAll(){
        return eventDao.getAll();
    }

    @Override
    public void deleteEvent(Event event){
        eventDao.delete(event);
    }

    @Override
    public Event updateEvent(Event event){
        return eventDao.update(event);
    }
}
