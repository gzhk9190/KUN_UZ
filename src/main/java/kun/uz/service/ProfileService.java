package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.config.details.CustomUserDetails;
import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileStatus;
import kun.uz.exp.AppBadRequestException;
import kun.uz.repository.ProfileRepository;
import kun.uz.validation.ProfileValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final CustomUserDetails customUserDetails;

    public ApiResponse<ProfileResponseDTO> create(@Valid ProfileRequestDTO dto) {
        if (Objects.isNull(dto.getName())){
            return ApiResponse.badRequest("Profile name null bo'lmasligi kerak");
        }
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrueAndStatus(dto.getEmail(), ProfileStatus.ACTIVE);
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday Email li profile allaqachon mavjud");
        }
        if(Objects.isNull(dto.getPhone())&&Objects.isNull(dto.getEmail())){
            return ApiResponse.badRequest("Profileda Email yoki Phone bo'sh bo'lmasligi kerak");
        }
        if (Objects.isNull(dto.getPassword())){
            return ApiResponse.badRequest("Profileda Password bo'lishi shart");
        }

        Optional<ProfileEntity> optional1 = profileRepository.findByPhoneAndVisibleIsTrueAndStatus(dto.getPhone(), ProfileStatus.ACTIVE);
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Bunday Phone li profile allaqachon mavjud");
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setSurname(dto.getSurname());
        entity.setPhotoId(dto.getPhotoId());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        ProfileEntity saved = profileRepository.save(entity);
        return ApiResponse.success(ProfileResponseDTO.toDTO(saved));
    }

    public ApiResponse<ProfileResponseDTO> update(String id, @Valid ProfileRequestDTO dto) {
        if (Objects.isNull(dto.getName())){
            return ApiResponse.badRequest("Profile name null bo'lmasligi kerak");
        }
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrueAndStatus(dto.getEmail(), ProfileStatus.ACTIVE);
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday Email li profile allaqachon mavjud");
        }
        if(Objects.isNull(dto.getPhone())&&Objects.isNull(dto.getEmail())){
            return ApiResponse.badRequest("Profileda Email yoki Phone bo'sh bo'lmasligi kerak");
        }
        if (Objects.isNull(dto.getPassword())){
            return ApiResponse.badRequest("Profileda Password bo'lishi shart");
        }

        Optional<ProfileEntity> optional1 = profileRepository.findByPhoneAndVisibleIsTrueAndStatus(dto.getPhone(), ProfileStatus.ACTIVE);
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Bunday Phone li profile allaqachon mavjud");
        }

        ProfileEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhotoId(dto.getPhotoId());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        ProfileEntity saved = profileRepository.save(entity);
        return ApiResponse.success(ProfileResponseDTO.toDTO(saved));
    }

    public ApiResponse<ProfileResponseDTO> getById(String id) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleIsTrue(id);
        return optional.map(profileEntity -> ApiResponse.success(ProfileResponseDTO.toDTO(profileEntity))).orElseGet(() -> ApiResponse.badRequest("Bunday profile mavjud emas"));
    }

    private ProfileEntity get(String id) {
        return profileRepository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<ProfileResponseDTO>> getAll() {
        return ApiResponse.success(profileRepository.findAllByVisibleIsTrue().stream().map(ProfileResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        profileRepository.updateVisible(id);
        return true;
    }

    public ApiResponse<ProfileResponseDTO> updateDetail(@Valid ProfileRequestDTO dto) {
        if (Objects.isNull(dto.getName())){
            return ApiResponse.badRequest("Profile name null bo'lmasligi kerak");
        }
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleIsTrueAndStatus(dto.getEmail(), ProfileStatus.ACTIVE);
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday Email li profile allaqachon mavjud");
        }
        if(Objects.isNull(dto.getPhone())&&Objects.isNull(dto.getEmail())){
            return ApiResponse.badRequest("Profileda Email yoki Phone bo'sh bo'lmasligi kerak");
        }
        if (Objects.isNull(dto.getPassword())){
            return ApiResponse.badRequest("Profileda Password bo'lishi shart");
        }

        Optional<ProfileEntity> optional1 = profileRepository.findByPhoneAndVisibleIsTrueAndStatus(dto.getPhone(), ProfileStatus.ACTIVE);
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Bunday Phone li profile allaqachon mavjud");
        }

        ProfileEntity entity = get(customUserDetails.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setPhotoId(dto.getPhotoId());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        ProfileEntity saved = profileRepository.save(entity);
        return ApiResponse.success(ProfileResponseDTO.toDTO(saved));
    }

    public Page<ProfileResponseDTO> getPagination(Pageable pageable) {
        return profileRepository.findByVisible(true, pageable)
                .map(this::toDTO);
    }
    public ProfileResponseDTO toDTO(ProfileEntity entity) {
        return ProfileResponseDTO.toDTO(entity);
    }

    public ApiResponse<ProfileResponseDTO> updatePhoto(@Valid String photoId) {
        if (photoId == null || photoId.trim().isEmpty()){
            throw new AppBadRequestException("Photo Id is null or empty");
        }

        ProfileEntity entity = get(customUserDetails.getId());
        entity.setPhotoId(photoId);
        ProfileEntity saved = profileRepository.save(entity);
        return ApiResponse.success(ProfileResponseDTO.toDTO(saved));
    }
}
