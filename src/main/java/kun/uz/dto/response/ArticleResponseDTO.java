package kun.uz.dto.response;

import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.entities.ArticleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleResponseDTO extends ArticleRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
    public ArticleResponseDTO toDTO(ArticleEntity entity) {
        ArticleResponseDTO response = new ArticleResponseDTO();
        response.setId(entity.getId());
        response.setVisible(entity.getVisible());
        response.setCreatedDate(entity.getCreateDate());
        response.setModeratorId(entity.getModerator().getId());
        response.setContent(entity.getContent());
        response.setCategoryId(entity.getCategory().getId());
        response.setPublisherId(entity.getPublisher().getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        response.setRegionId(entity.getRegion().getId());
        response.setImageId(entity.getImageId());
        response.setSharedCount(entity.getSharedCount());
        response.setStatus(entity.getStatus());
        return response;
    }
}
