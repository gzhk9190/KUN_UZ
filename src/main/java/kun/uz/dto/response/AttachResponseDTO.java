package kun.uz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.AttachEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor()
public class AttachResponseDTO {
    String id;
    String url;
    String extension;
    String originName;
    LocalDateTime createdDate;

    public AttachResponseDTO(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public AttachResponseDTO(String url) {
        this.url = url;
    }

    public static AttachResponseDTO toDTO(String domain,AttachEntity entity) {
        AttachResponseDTO response = new AttachResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setUrl(domain + "/attach/download/" + entity.getId());
        response.setExtension(entity.getExtension());
        response.setOriginName(entity.getOriginName());
        return response;
    }
}
