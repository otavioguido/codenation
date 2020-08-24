package projeto.pratico.data.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.pratico.data.exception.EventException;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.service.EventService;
import projeto.pratico.data.specification.EventSpec;

import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> postEvent(@RequestBody Event event){
        try {
            return new ResponseEntity<>(eventService.saveEvent(event), HttpStatus.CREATED);
        } catch (EventException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<Page<Event>> getEventsByParam(EventSpec eventSpec, Pageable pageRequest){
        return new ResponseEntity<>(eventService.findAll(eventSpec, pageRequest), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventBy(@PathVariable String id){
        Event event = eventService.findById(UUID.fromString(id)).orElse(null);
        log.info(event.toString());
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Event> deleteEvent(Event event){
        eventService.deleteEvent(event);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateEvent(@RequestBody Event event){
        try {
            return new ResponseEntity<>(eventService.updateEvent(event), HttpStatus.CREATED);
        } catch (EventException e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
