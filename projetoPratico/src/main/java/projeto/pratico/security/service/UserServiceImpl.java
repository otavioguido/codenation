package projeto.pratico.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import projeto.pratico.security.dao.UserDao;
import projeto.pratico.security.dto.UserDTO;
import projeto.pratico.security.model.User;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public Page<User> findAll(Pageable pageable){
        return userDao.findAll(pageable);
    }

    @Override
    public User saveUser(UserDTO user) throws Exception {
        return userDao.saveUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }
}
