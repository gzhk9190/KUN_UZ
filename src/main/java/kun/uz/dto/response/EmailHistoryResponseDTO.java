package kun.uz.dto.response;


import kun.uz.dto.request.EmailHistoryRequestDTO;
import kun.uz.dto.request.EmailSMSRequestDTO;
import kun.uz.entities.EmailHistoryEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailHistoryResponseDTO extends EmailHistoryRequestDTO {
    String id;
    Boolean visible;
    LocalDateTime createdDate;

    public static EmailHistoryResponseDTO toDTO(EmailHistoryEntity emailHistoryEntity) {
        EmailHistoryResponseDTO emailHistoryResponseDTO = new EmailHistoryResponseDTO();
        emailHistoryResponseDTO.setId(emailHistoryEntity.getId());
        emailHistoryResponseDTO.setVisible(emailHistoryEntity.getVisible());
        emailHistoryResponseDTO.setCreatedDate(emailHistoryEntity.getCreateDate());
        emailHistoryResponseDTO.setMessage(emailHistoryEntity.getMessage());
        emailHistoryResponseDTO.setEmail(emailHistoryEntity.getEmail());
        return emailHistoryResponseDTO;
    }
}
