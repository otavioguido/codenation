package projeto.pratico.data.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface Dao<T> {

    Optional<T> get(UUID id);

    List<T> getAll();

    void save(T t) throws Exception;

    void update(T t, Map<String, Object> params) throws Exception;

    void delete(T t);
}
