package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavedArticleRequestDTO {
    String profileId;
    String articleId;

    public SavedArticleRequestDTO() {
    }
}
