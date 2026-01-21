package kun.uz;

import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KunUzApplicationTests {
    @Autowired
    private AuthService authService;

    @Test
    void contextLoads() {
        RegistrationRequestDTO dto = new RegistrationRequestDTO();

        authService.registration(dto);
    }

}
