package kun.uz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import kun.uz.dto.request.CommentLikeRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.CommentLikeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentLikeResponseDTO extends CommentLikeRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;
    public static CommentLikeResponseDTO toDTO(CommentLikeEntity entity) {
        CommentLikeResponseDTO response = new CommentLikeResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setCommentId(entity.getComment().getId());
        response.setProfileId(entity.getProfile().getId());
        return response;
    }
}
