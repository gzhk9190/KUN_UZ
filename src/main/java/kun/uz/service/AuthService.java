package kun.uz.service;

import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.entities.EmailSMSEntity;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileRole;
import kun.uz.enums.ProfileStatus;
import kun.uz.repository.EmailSMSRepository;
import kun.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author 'Bilol Tuxtamurodov' on 24.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */

@Service
public class AuthService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailSMSRepository emailSMSRepository;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private Random random;

    @Value("${spring.mail.username}")
    private String fromAccount;
    @Autowired
    private JavaMailSender javaMailSender;

    public String send(String to, Integer code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAccount);
        msg.setTo(to);
        msg.setSubject("Tasdiqlash kodi");
        msg.setText("Sizning tasdiqlash kodingiz : " + code + "\nHammaga tarqating");
        javaMailSender.send(msg);

        return "Mail was send";
    }

    public ApiResponse<String> registration(RegistrationRequestDTO dto) {
        // email bor yo'qligiga tekshiring
        // photoId ham tekshirilsin
        ProfileEntity entity = new ProfileEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setPhotoId(dto.getPhotoId());
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setStatus(ProfileStatus.NOT_ACTIVE);
        profileRepository.save(entity);

        Integer code = random.nextInt(10000,99999);
        EmailSMSEntity smsEntity = new EmailSMSEntity();
        smsEntity.setEmail(dto.getEmail());
        smsEntity.setCode(code);
        smsEntity.setUsed(false);
        emailSMSRepository.save(smsEntity);
        send(dto.getEmail(), code);
        return ApiResponse.success("Zo'rkuuu");
    }
}
