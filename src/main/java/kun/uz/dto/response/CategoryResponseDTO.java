package kun.uz.dto.response;

import kun.uz.dto.request.CategoryRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponseDTO extends CategoryRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
}
