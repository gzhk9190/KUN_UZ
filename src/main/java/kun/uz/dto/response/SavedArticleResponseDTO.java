package kun.uz.dto.response;

import kun.uz.dto.request.SavedArticleRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.SavedArticleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SavedArticleResponseDTO extends SavedArticleRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public SavedArticleResponseDTO toDTO(SavedArticleEntity entity) {
        SavedArticleResponseDTO response = new SavedArticleResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setArticleId(entity.getArticle().getId());
        response.setProfileId(entity.getProfile().getId());
        return response;
    }
}
