package kun.uz.controller;

import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/registration")
    public ApiResponse<String> sendEmail(@RequestBody RegistrationRequestDTO dto) {
        return authService.registration(dto);
    }

    // TODO code va email
    // agar 1 daqiqadan o'tib ketsa code yaraqosiz ekanligini ayting so'kmasdan
    @PostMapping("/verification")
    public ApiResponse<String> verification(@RequestBody RegistrationRequestDTO dto) {
        return authService.registration(dto);
    }
    // qayta email code jonatishda barcha qolgan code larni used true qilib qo'yasizlar  yoki visible false qilasizlar

    // forget password agar passwordni unutsa shu api ga email keladi siz code jonatasiz
}
