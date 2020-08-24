package projeto.pratico.security.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import projeto.pratico.security.dto.UserDTO;
import projeto.pratico.security.model.User;

public interface UserService {
    Page<User> findAll(Pageable pageable);

    User saveUser(UserDTO user) throws Exception;
}
