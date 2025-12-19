package kun.uz.dto.response;

import kun.uz.dto.request.RegionRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.RegionEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionResponseDTO extends RegionRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public static RegionResponseDTO toDTO(RegionEntity entity) {
        RegionResponseDTO response = new RegionResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setNameEn(entity.getNameEn());
        response.setNameUz(entity.getNameUz());
        response.setNameRu(entity.getNameRu());
        return response;
    }
}
