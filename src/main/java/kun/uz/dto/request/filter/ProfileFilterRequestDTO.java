package kun.uz.dto.request.filter;

import kun.uz.enums.ProfileRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

/**
 * @author 'Bilol Tuxtamurodov' on 26.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileFilterRequestDTO {
    String title,phone;
    ProfileRole role;
    LocalDate created_date_from,created_date_to;
}
