package projeto.pratico.data.model;




import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode(exclude = {"id", "createdAt", "quantity"})
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Setter
    @NotNull
    @NotBlank
    @Column(name = "log_level")
    private String logLevel;

    @Setter
    @NotNull
    @NotBlank
    @Column(name = "event_description")
    private String eventDescription;

    @Setter
    @NotNull
    @NotBlank
    @Column(name = "event_log")
    private String eventLog;

    @Setter
    @NotNull
    @NotBlank
    private String origin;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Setter
    @NotNull
    @NotBlank
    private Long quantity;
}
