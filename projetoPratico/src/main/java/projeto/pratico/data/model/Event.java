package projeto.pratico.data.model;




import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotNull
    @NotBlank
    @Column(name = "log_level")
    private String logLevel;

    @NotNull
    @NotBlank
    @Column(name = "event_description")
    private String eventDescription;

    @NotNull
    @NotBlank
    @Column(name = "event_log")
    private String eventLog;

    @NotNull
    @NotBlank
    private String origin;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @NotNull
    @NotBlank
    private Double quantity;
}
