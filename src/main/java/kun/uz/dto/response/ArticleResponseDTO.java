package kun.uz.dto.response;

import kun.uz.dto.request.ArticleRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleResponseDTO extends ArticleRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
}
