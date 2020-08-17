package projeto.pratico.data.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import projeto.pratico.data.model.Event;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {

    List<Event> findByLogLevel(String logLevel, Pageable pageable);

    List<Event> findByEventDescription(String eventDescription, Pageable pageable);

    List<Event> findByEventLog(String eventLog, Pageable pageable);

    List<Event> findByOrigin(String origin, Pageable pageable);

    List<Event> findByCreatedAt(String createdAt, Pageable pageable);

    List<Event> findByQuantity(String quantity, Pageable pageable);
}
