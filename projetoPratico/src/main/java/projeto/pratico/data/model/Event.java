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
@EqualsAndHashCode(exclude = {"id", "createdAt", "quantity", "modifiedDate"})
@EntityListeners(AuditingEntityListener.class)
public class Event {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private UUID id;

    @Setter
    @NotNull
    @Column(name = "log_level")
    @Enumerated(EnumType.STRING)
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
    private Double quantity;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @NotNull
    private LocalDateTime createdAt;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;
}
