package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleArticleTypeRequestDTO {
    String articleId;
    String articleTypeId;

    public ArticleArticleTypeRequestDTO() {
    }
}
