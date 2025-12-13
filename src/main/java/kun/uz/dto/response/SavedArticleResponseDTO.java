package kun.uz.dto.response;

import kun.uz.dto.request.SavedArticleRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavedArticleResponseDTO extends SavedArticleRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
}
