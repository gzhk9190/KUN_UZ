package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link kun.uz.entities.RegionEntity}
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionRequestDTO {
    Integer orderNumber;
    String nameUz;
    String nameRu;
    String nameEn;

    public RegionRequestDTO() {
    }
}