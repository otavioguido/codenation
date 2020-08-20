package projeto.pratico.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto.pratico.security.dto.UserDTO;
import projeto.pratico.security.model.User;
import projeto.pratico.security.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Page<User> findAll(Pageable pageable){
        return userRepository.findAll(pageable);
    }

    private User saveUser(User user){
        return userRepository.saveAndFlush(user);
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
