package projeto.pratico.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import projeto.pratico.security.dto.UserDTO;
import projeto.pratico.security.model.User;
import projeto.pratico.security.repository.UserRepository;

import java.util.Optional;

@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(UserDTO user) throws Exception {
        User userDB = userRepository.findByUsername(user.getUsername()).orElse(null);

        if (userDB == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            return userRepository.saveAndFlush(user.user());
        }

        throw new Exception("User " + user.toString() + " already exist.");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElse(null);

        if (user != null){
            return new UserDTO(user);
        }

        throw new UsernameNotFoundException(username);
    }
}
