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

/**
 * https://docs.spring.io/spring-security/site/docs/5.0.7.RELEASE/reference/html/oauth2login-advanced.html
 * https://docs.spring.io/spring-security-oauth2-boot/docs/current/reference/html5/
 * https://dzone.com/articles/secure-spring-rest-with-spring-security-and-oauth2#:~:text=A%20Resource%20Server%20serves%20resources,via%20an%20incoming%20OAuth2%20token
 * https://medium.com/cwi-software/oauth2-com-spring-boot-2-e-spring-5-e7bfb7c58d4a
 * https://www.marcobehler.com/guides/spring-security
 * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#mvc
 * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#oauth2resourceserver-bearertoken-resolver
 * https://howtodoinjava.com/spring-boot2/oauth2-auth-server/
 * */

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
            User usuario = new User();
            usuario.setUsername("admin");
            usuario.setPassword(passwordEncoder().encode("admin"));
            userRepository.save(usuario);
        }

        builder.userDetailsService(username -> new UserDTO(userRepository.findByUsername(username).orElse(null))).passwordEncoder(passwordEncoder());
    }
}
