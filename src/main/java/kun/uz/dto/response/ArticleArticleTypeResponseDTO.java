package kun.uz.dto.response;

import kun.uz.dto.request.ArticleArticleTypeRequestDTO;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleArticleTypeResponseDTO extends ArticleArticleTypeRequestDTO {
    String id;

    public ArticleArticleTypeResponseDTO() {

    }
}
