package kun.uz.dto.request.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 24.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegistrationRequestDTO {
    String name;
    String surname;
    String email;
    String phone;
    String password;
    String photoId;
}
