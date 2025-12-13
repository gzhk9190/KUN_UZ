package kun.uz.dto.response;

import kun.uz.dto.request.ArticleLikeRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleLikeResponseDTO extends ArticleLikeRequestDTO {
    public ArticleLikeResponseDTO(String profile_id, String article_id) {
        super(profile_id, article_id);
    }
    String id;
    LocalDateTime createdDate;
    Boolean visible;
}
