package kun.uz.dto.response;

import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleTypeResponseDTO extends ArticleTypeRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public static ArticleTypeResponseDTO toDTO(ArticleTypeEntity entity) {
        ArticleTypeResponseDTO response = new ArticleTypeResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setNameEn(entity.getNameEn());
        response.setNameUz(entity.getNameUz());
        response.setNameRu(entity.getNameRu());
        return response;
    }
}
