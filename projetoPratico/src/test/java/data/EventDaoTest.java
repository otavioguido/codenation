package data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;
import projeto.pratico.data.dao.EventDaoImpl;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.model.LogLevel;
import projeto.pratico.data.repository.EventRepository;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EventDaoTest {

    private static final String EVENT_DESCRIPTION_1 = "event_description_1";
    private static final String EVENT_LOG_1 = "event_log_1";
    private static final String ORIGIN_1 = "origin_1";

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventDaoImpl eventDao;

    @Test
    public void tryToSaveCorrectEvent_IsSuccessful(){
        Event event = new Event().builder()
                .eventDescription(EVENT_DESCRIPTION_1)
                .eventLog(EVENT_LOG_1)
                .logLevel(LogLevel.INFO)
                .origin(ORIGIN_1)
                .build();

        when(eventDao.save(event)).thenReturn(event);

        Event savedEvent = eventDao.save(event);

        Assert.isTrue(savedEvent.equals(event),
                "Saved event: " + savedEvent.toString() +
                "\nEvent passed: " + event.toString());
    }
}
