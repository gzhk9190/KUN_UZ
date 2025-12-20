package kun.uz.dto.response;

import kun.uz.dto.request.CommentRequestDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.CommentEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;


@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDTO extends CommentRequestDTO {
    String id;
    LocalDateTime createdDate;
    Boolean visible;

    public static CommentResponseDTO toDTO(CommentEntity entity) {
        CommentResponseDTO response = new CommentResponseDTO();
        response.setId(entity.getId());
        response.setCreatedDate(entity.getCreateDate());
        response.setVisible(entity.getVisible());
        response.setContent(entity.getContent());
        response.setProfileId(entity.getProfile().getId());
        response.setArticleId(entity.getArticle().getId());
        response.setReplyId(entity.getReplyId());
        response.setUpdatedDate(entity.getUpdatedDate());
        return response;
    }
}
