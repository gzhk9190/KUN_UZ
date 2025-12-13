package kun.uz.dto.response;

import kun.uz.dto.request.CommentRequestDTO;
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
}
