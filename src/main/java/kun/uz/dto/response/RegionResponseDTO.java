package kun.uz.dto.response;

import kun.uz.dto.request.RegionRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionResponseDTO extends RegionRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
}
