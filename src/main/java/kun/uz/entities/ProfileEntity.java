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
 * @author 'AzamSuxbatillayev' on 13.12.2025
 * @project KUN_UZ
 * @contact @bot_1_maker
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
    @Enumerated(EnumType.STRING)
    ProfileStatus status;
    @Enumerated(EnumType.STRING)
    ProfileRole role;

}


