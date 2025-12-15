package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleLikeRequestDTO {
    String profile_id;
    String article_id;

    public ArticleLikeRequestDTO() {
    }
}
