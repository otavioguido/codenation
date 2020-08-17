package projeto.pratico.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.service.EventService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public Event postEvent(Event event){
        return eventService.saveEvent(event);
    }

    @GetMapping
    public List<Event> getEvents(){
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event getEventBy(@RequestParam UUID uuid){
        return eventService.findById(uuid).orElse(null);
    }

    @DeleteMapping
    public void deleteEvent(Event event){
        eventService.deleteEvent(event);
    }

    @PutMapping
    public void updateEvent(Event event){
        eventService.updateEvent(event);
    }
}
