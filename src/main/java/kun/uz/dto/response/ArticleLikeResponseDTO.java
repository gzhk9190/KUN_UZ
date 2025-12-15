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

    public ArticleLikeResponseDTO(String profile_id, String article_id) {
        super(profile_id, article_id);
    }

    public ArticleLikeResponseDTO toDTO(ArticleLikeEntity entity) {
        ArticleLikeResponseDTO response = new ArticleLikeResponseDTO();
        response.setArticle_id(entity.getArticle().getId());
        response.setId(entity.getId());
        response.setVisible(entity.getVisible());
        response.setCreatedDate(entity.getCreateDate());
        response.setProfile_id(entity.getProfile().getId());
        return response;
    }
}
