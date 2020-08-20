package projeto.pratico.security.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Getter
@Setter
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

}
