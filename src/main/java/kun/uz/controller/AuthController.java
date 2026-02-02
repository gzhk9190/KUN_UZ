package kun.uz.controller;

import io.swagger.v3.oas.annotations.Operation;
import kun.uz.dto.JwtDTO;
import kun.uz.dto.request.auth.AuthRequestDTO;
import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author 'Bilol Tuxtamurodov' on 24.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */

@RestController
@RequestMapping("/api/v1/auth")

public class AuthController {
    @Autowired
    private AuthService authService;

    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping("/registration")
    public ApiResponse<String> sendEmail(@RequestBody RegistrationRequestDTO dto) {
        return authService.registration(dto);
    }

    // TODO code va email
    // agar 1 daqiqadan o'tib ketsa code yaraqosiz ekanligini ayting so'kmasdan

    @PostMapping("/verification/{jwt}")
    public ApiResponse<String> verification(@PathVariable("jwt") String jwt) {
        return authService.verification(jwt);
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody RegistrationRequestDTO dto){
        return ResponseEntity.ok(authService.login(dto));
    }
    @PreAuthorize(value = "hasRole('ROLE_USER')")
    @PostMapping("/Resend")
    public ApiResponse<String> resend(@RequestBody RegistrationRequestDTO dto) {
        return authService.resend(dto);
    }

    // qayta email code jonatishda barcha qolgan code larni used true qilib qo'yasizlar  yoki visible false qilasizlar
    // forget password agar passwordni unutsa shu api ga email keladi siz code jonatasiz
}
