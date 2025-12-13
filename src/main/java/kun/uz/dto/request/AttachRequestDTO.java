package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AttachRequestDTO {
    Integer orderNumber;
    String nameUz;
    String nameRu;
    String nameEn;
}
