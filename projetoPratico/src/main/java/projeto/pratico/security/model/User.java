package projeto.pratico.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
public class User {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    private String password;

    public User(@NotNull @NotBlank String username, @NotNull @NotBlank String password) {
        this.username = username;
        this.password = password;
    }
}
