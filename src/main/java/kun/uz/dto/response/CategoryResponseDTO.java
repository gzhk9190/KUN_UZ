package kun.uz.dto.response;

import kun.uz.dto.request.CategoryRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.CategoryEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryResponseDTO extends CategoryRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public static CategoryResponseDTO toDTO(CategoryEntity entity) {
        CategoryResponseDTO response = new CategoryResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setNameEn(entity.getNameEn());
        response.setNameUz(entity.getNameUz());
        response.setNameRu(entity.getNameRu());
        return response;
    }
}
