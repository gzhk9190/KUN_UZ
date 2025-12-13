package kun.uz.dto.response;

import kun.uz.dto.request.ProfileRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponseDTO extends ProfileRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
}
