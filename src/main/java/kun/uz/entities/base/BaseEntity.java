package kun.uz.entities.base;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * @author 'Bilol Tuxtamurodov' on 10.12.2025
 * @project Lesson_130
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    Boolean visible = true;

    @Column(name = "created_date")
    @CreationTimestamp
    LocalDateTime createDate;
}
