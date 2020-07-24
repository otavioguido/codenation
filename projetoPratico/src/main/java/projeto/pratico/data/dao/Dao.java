package projeto.pratico.data.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projeto.pratico.data.model.Event;
import projeto.pratico.data.specification.EventSpec;

import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {
    Optional<T> get(UUID id);

    Page<T> getAll(EventSpec eventSpec, Pageable pageRequest);

    Event save(T t);

    Event update(T t);

    void delete(T t);
}
