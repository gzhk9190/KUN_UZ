package kun.uz.exp.handler;



import jakarta.persistence.NonUniqueResultException;
import kun.uz.dto.response.ApiResponse;
import kun.uz.exp.*;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({ItemAlreadyExistsException.class, ItemNotFoundException.class,
            AppBadRequestException.class, NullPointerException.class, IllegalArgumentException.class,
            FileUploadException.class, NonUniqueResultException.class, SQLException.class, PasswordOrEmailWrongException.class, EmailAlreadyExistsException.class, JwtTimedOutException.class})
    public ResponseEntity<?> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.badRequest().body(ApiResponse.badRequest(e.getMessage()));
    }

    @ExceptionHandler({TokenNotValidException.class, UsernameNotFoundException.class,
            BadCredentialsException.class})
    public ResponseEntity<ApiResponse<?>> handleTokenException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ApiResponse.forbidden(e.getMessage()));
    }


    @ExceptionHandler({AppForbiddenException.class, AccessDeniedException.class})
    public ResponseEntity<?> handleForbiddenException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
    }

    @ExceptionHandler({AppNotAcceptableException.class})
    public ResponseEntity<?> handleNotAcceptableException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
    }


}
