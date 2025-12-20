package kun.uz.dto.response;

import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.ProfileEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileResponseDTO extends ProfileRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
  public static  ProfileResponseDTO toDTO(ProfileEntity entity) {
       ProfileResponseDTO response = new ProfileResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setPassword(entity.getPassword());
        response.setPhone(entity.getPhone());
        response.setPhotoId(entity.getPhotoId());
        response.setRole(entity.getRole());
        response.setStatus(entity.getStatus());
        response.setSurname(entity.getSurname());
        return response;
    }
}
