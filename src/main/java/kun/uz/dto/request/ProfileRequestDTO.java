package kun.uz.dto.request;

import kun.uz.enums.ProfileRole;
import kun.uz.enums.ProfileStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

/**
 * DTO for {@link kun.uz.entities.ProfileEntity}
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequestDTO {
    String name;
    String surname;
    String email;
    String phone;
    String password;
    String photoId;
    ProfileStatus status;
    ProfileRole role;

    public ProfileRequestDTO() {
    }
}