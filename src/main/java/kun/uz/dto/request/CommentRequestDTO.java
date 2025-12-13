package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDTO {
    LocalDateTime updatedDate;
    String profileId;
    String content;
    String articleId;
    String replyId;

    public CommentRequestDTO() {
    }
}
