package kun.uz.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kun.uz.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 24.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */

@Entity
@Getter
@Setter
@Table(name = "email_sms")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailSMSEntity extends BaseEntity {
    String email;
    Integer code;
    Boolean used;

}
