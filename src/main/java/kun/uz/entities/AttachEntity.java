package kun.uz.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import kun.uz.entities.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Table(name = "attach")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachEntity extends BaseEntity {
    String path;
    String extension;
    String originName;
    Long size;
}
