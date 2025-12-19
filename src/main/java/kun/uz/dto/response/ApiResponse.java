package kun.uz.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author 'Bilol Tuxtamurodov' on 19.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    String message;
    Boolean isError;
    T data;
    Integer code;

    public static ApiResponse<?> success() {
        return new ApiResponse<>("Success", true, null, 200);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("Success", true, data, 200);
    }

    public static <T> ApiResponse<T> badRequest(String message) {
        return new ApiResponse<>(message, false, null, 400);
    }

    public static <T> ApiResponse<T> forbidden(String message) {
        return new ApiResponse<T>(message, false, null, 403);
    }
}
