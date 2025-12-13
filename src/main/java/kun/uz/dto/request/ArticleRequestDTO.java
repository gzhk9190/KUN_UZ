package kun.uz.dto.request;

import kun.uz.enums.ArticleStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * DTO for {@link kun.uz.entities.ArticleEntity}
 */
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleRequestDTO {
    String title;
    String description;
    String content;
    Integer sharedCount;
    String imageId;
    String regionId;
    String categoryId;
    String moderatorId;
    String publisherId;
    ArticleStatus status;

    public ArticleRequestDTO() {
    }
}