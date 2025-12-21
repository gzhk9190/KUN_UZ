package kun.uz.dto.request;

import kun.uz.enums.CommentLikeStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentLikeRequestDTO {
    String profileId;
    String commentId;
    CommentLikeStatus status;
    public CommentLikeRequestDTO() {
    }
}
