package projeto.pratico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ProjetoPraticoApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProjetoPraticoApplication.class, args);
    }
}
