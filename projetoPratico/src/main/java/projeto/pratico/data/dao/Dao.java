package projeto.pratico.data.dao;

import projeto.pratico.data.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {
    Optional<T> get(UUID id);

    List<T> getAll();

    Event save(T t);

    Event update(T t);

    void delete(T t);
}
