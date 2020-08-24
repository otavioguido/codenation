package projeto.pratico.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import projeto.pratico.security.dto.UserDTO;
import projeto.pratico.security.model.User;
import projeto.pratico.security.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfigAdapter extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void authenticationManager(AuthenticationManagerBuilder builder, UserRepository userRepository) throws Exception {
        if (userRepository.count() == 0) {
            UserDTO usuario = new UserDTO();
            usuario.setUsername("admin");
            usuario.setPassword(passwordEncoder().encode("admin"));
            userRepository.save(usuario.user());
        }

        builder.userDetailsService(username -> new UserDTO(userRepository.findByUsername(username).orElse(null))).passwordEncoder(passwordEncoder());
    }
}
