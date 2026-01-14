package kun.uz.dto.request.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthRequestDTO {
    private String email;
    private String phone;
    private String password;
}
