package projeto.pratico.data.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.service.EventService;
import projeto.pratico.data.specification.EventSpec;

import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public Event postEvent(@RequestBody Event event){
        return eventService.saveEvent(event);
    }

    @GetMapping
    public Page<Event> getEventsByParam(EventSpec eventSpec, Pageable pageRequest){
        return eventService.findAll(eventSpec, pageRequest);
    }

    @GetMapping("/{id}")
    public Event getEventBy(@PathVariable String id){
        return eventService.findById(UUID.fromString(id)).orElse(null);
    }

    @DeleteMapping
    public void deleteEvent(Event event){
        eventService.deleteEvent(event);
    }

    @PutMapping
    public void updateEvent(@RequestBody Event event){
        eventService.updateEvent(event);
    }
}
