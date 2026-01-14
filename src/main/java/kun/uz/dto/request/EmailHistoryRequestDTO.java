package kun.uz.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.lang.NonNullApi;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailHistoryRequestDTO {
    String email;
    String message;


}
