package kun.uz.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
}
