package kun.uz.entities;

import jakarta.persistence.*;
import kun.uz.entities.base.BaseEntity;
import kun.uz.enums.ProfileRole;
import kun.uz.enums.ProfileStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 10.12.2025
 * @project Lesson_130
 * @contact @BilolTuxtamurodov
 */

@Entity
@Getter
@Setter
@Table(name = "profile")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileEntity extends BaseEntity {
    @Column(nullable = false)
    String name;
    String surname;
    @Column(nullable = false,unique = true)
    String email;
    @Column(nullable = false,unique = true)
    String phone;
    @Column(nullable = false)
    String password;
    String photoId;
    @Enumerated
    ProfileStatus status;
    @Enumerated(EnumType.STRING)
    ProfileRole role;

}


