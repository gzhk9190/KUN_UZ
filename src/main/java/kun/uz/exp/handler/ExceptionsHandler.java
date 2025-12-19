package kun.uz.exp.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * @author 'Mukhtarov Sarvarbek' on 01.02.2024
 * @project tdtu_call_centre
 * @contact @sarvargo
 */
@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<?>> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String type = ex.getRequiredType().getSimpleName();
        Object value = ex.getValue();
        String message = String.format("'%s' should be a valid '%s' and '%s' isn't",
                name, type, value);

        System.out.println(message);
        // Do the graceful handling
        return ResponseEntity.ok(ApiResponse.badRequest("Malumotlar xato kiritildi!"));
    }

}
