package kun.uz.dto.response;

import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.entities.ArticleEntity;
import kun.uz.entities.ArticleLikeEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleLikeResponseDTO extends ArticleLikeRequestDTO {

    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public ArticleLikeResponseDTO() {
    }

    public static ArticleLikeResponseDTO toDTO(ArticleLikeEntity entity) {
        ArticleLikeResponseDTO response = new ArticleLikeResponseDTO();
        response.setArticleId(entity.getArticle().getId());
        response.setId(entity.getId());
        response.setVisible(entity.getVisible());
        response.setCreatedDate(entity.getCreateDate());
        response.setProfileId(entity.getProfile().getId());
        return response;
    }
}
