package kun.uz.dto.response;

import kun.uz.dto.request.EmailSMSRequestDTO;
import kun.uz.entities.CommentEntity;
import kun.uz.entities.EmailSMSEntity;
import kun.uz.entities.base.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailSMSResponseDTO extends EmailSMSRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public static EmailSMSResponseDTO toDTO(EmailSMSEntity entity) {
        EmailSMSResponseDTO response = new EmailSMSResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setEmail(entity.getEmail());
        response.setCode(entity.getCode());
        response.setUsed(entity.getUsed());
        return response;
    }
}
