package projeto.pratico.security.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import projeto.pratico.security.dto.UserDTO;
import projeto.pratico.security.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserDao {
    Page<User> findAll(Pageable pageable);

    Optional<User> findByUsername(String username);

    User saveUser(UserDTO user) throws Exception;

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
