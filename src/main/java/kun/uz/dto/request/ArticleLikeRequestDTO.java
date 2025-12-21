package kun.uz.dto.request;

import kun.uz.enums.ArticleLikeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleLikeRequestDTO {
    String profileId;
    String articleId;
    ArticleLikeStatus status;
    public ArticleLikeRequestDTO() {
    }
}
