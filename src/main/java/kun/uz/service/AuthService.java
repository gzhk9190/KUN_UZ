package kun.uz.service;

import io.jsonwebtoken.JwtException;
import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.entities.EmailSMSEntity;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileRole;
import kun.uz.enums.ProfileStatus;
import kun.uz.exp.*;
import kun.uz.repository.EmailSMSRepository;
import kun.uz.repository.ProfileRepository;
import kun.uz.util.JwtUtil;
import kun.uz.validation.RegistrationValidation;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

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

    public void send(String to, Integer code) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAccount);
        msg.setTo(to);
        msg.setSubject("Tasdiqlash kodi");
        msg.setText("Sizning tasdiqlash kodingiz : " + code + "\nHammaga tarqating");
        javaMailSender.send(msg);
    }

    public ApiResponse<String> registration(RegistrationRequestDTO dto) {
        // email bor yo'qligiga tekshiring
        // photoId ham tekshirilsin

        RegistrationValidation.isValid(dto);
        ProfileEntity profileEntity = profileRepository.findByEmailAndVisibleIsTrue(dto.getEmail());

        if (profileEntity != null) {
            if (!profileEntity.getStatus().equals(ProfileStatus.NOT_ACTIVE)) {
                throw new EmailAlreadyExistsException("Email Already Exists");
            } else {
                profileRepository.delete(profileEntity);
            }
        }
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

        Integer code = random.nextInt(10000, 99999);
        EmailSMSEntity smsEntity = new EmailSMSEntity();
        smsEntity.setEmail(dto.getEmail());
        smsEntity.setCode(code);
        smsEntity.setUsed(false);
        emailSMSRepository.save(smsEntity);
        send(dto.getEmail(), code);

        return ApiResponse.success("Zo'rkuuu");
    }
    public ApiResponse<String> verification(String jwt){
        String userId = null;
        try {
            userId = JwtUtil.decode(jwt).getId();
        } catch (JwtException e) {
            throw new AppBadRequestException("Verification not completed");
        }
        EmailSMSEntity currentEmailSMS = emailSMSRepository.getByEmailAndVisibleIsTrue(profileRepository.findByIdAndVisibleIsTrue(userId).get().getEmail(),true);
        if (currentEmailSMS != null) {
            if (currentEmailSMS.getUsed()) {
                throw new EmailAlreadyExistsException("Email is already in use");
            }
            if (LocalDateTime.now().isAfter(currentEmailSMS.getCreateDate().plusMinutes(3))){
                throw new JwtTimedOutException("Bu parol ning yaroqlilik muddati tugagan! ");
            }
            currentEmailSMS.setUsed(true);
            emailSMSRepository.save(currentEmailSMS);
        }
        profileRepository.updateStatusById(ProfileStatus.ACTIVE, userId);
        return ApiResponse.success("User Id :"+userId+" Ga teng bo'lgan Profile muvoffaqiyatli ro'yxatdan o'tdi!!!");
    }

    public ProfileResponseDTO login(RegistrationRequestDTO dto) {
        String pswd = passwordEncoder.encode(dto.getPassword());
        ProfileEntity profileEntity = profileRepository.findByEmailAndPasswordAndVisibleIsTrue(dto.getEmail(),pswd);
        if (profileEntity == null) {
            throw new ItemNotFoundException("Email Already Exists");
        }
        if (!profileEntity.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppForbiddenException("Email Already Exists");
        }
        return ProfileResponseDTO.toDTO(profileEntity);
    }
}
