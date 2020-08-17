package projeto.pratico.data.model;




import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EntityListeners(AuditingEntityListener.class)
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Setter
    @NotNull
    @NotBlank
    @Column(name = "log_level")
    private LogLevel logLevel;

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

    @Setter
    @NotNull
    @NotBlank
    private Double quantity;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;
}
