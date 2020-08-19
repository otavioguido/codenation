package projeto.pratico.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import projeto.pratico.data.dao.Dao;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.specification.EventSpec;

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
    public Page<Event> findAll(EventSpec eventSpec, Pageable pageRequest) {
        return eventDao.getAll(eventSpec, pageRequest);
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
