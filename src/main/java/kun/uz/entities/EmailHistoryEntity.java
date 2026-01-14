package kun.uz.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kun.uz.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RestController;


@Entity
@Getter
@Setter
@Table(name = "email_history")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailHistoryEntity extends BaseEntity {
    String email;
    String message;
}
