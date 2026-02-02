package kun.uz.service;

import jakarta.persistence.criteria.Predicate;
import jakarta.validation.Valid;
import kun.uz.config.details.CustomUserDetails;
import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.dto.request.filter.ProfileFilterRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileStatus;
import kun.uz.repository.ProfileRepository;
import kun.uz.validation.ProfileValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final AttachService attachService;

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

    public ApiResponse<Page<ProfileResponseDTO>> filter(ProfileFilterRequestDTO dto, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Specification<ProfileEntity> specification = ((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(cb.equal(root.get("visible"), true));
            query.distinct(true);

            if (Objects.nonNull(dto.getTitle())) {
                String pattern = "%" + dto.getTitle().toLowerCase() + "%";
                Predicate name = cb.like( cb.lower(root.get("name")), pattern );
                Predicate surname = cb.like( cb.lower( root.get("surname")), pattern);

                predicates.add(cb.or(name, surname));
            }
            // o'zila qilasizlar
            //??_NImani_??(vd da etmapsiz 😭)
            return cb.and(predicates.toArray(new Predicate[0]));
        });

        Page<ProfileEntity> list = profileRepository.findAll(specification, pageable);

        return ApiResponse.success(list.map(ProfileResponseDTO::toDTO));
    }

    public  Page<ProfileResponseDTO> getPagination(Pageable pageable) {
        return profileRepository.findByVisible(true, pageable).map(this::toDTO);
    }
    public ProfileResponseDTO toDTO(ProfileEntity entity) {
       return ProfileResponseDTO.toDTO(entity);
    }

    public ApiResponse<ProfileResponseDTO> updatePhoto(@Valid String photoId) {
        CustomUserDetails customUserDetails = new CustomUserDetails();
        ProfileEntity profile = get(customUserDetails.getId());


        if (attachService.get(profile.getPhotoId()) != null){
            attachService.delete(profile.getPhotoId());
        }
        profileRepository.updateAttachId(photoId, profile.getId());

        return ApiResponse.success(ProfileResponseDTO.toDTO(profile));
    }

    public ApiResponse<ProfileResponseDTO> updateDetail(@Valid ProfileRequestDTO profileRequestDTO) {
        ProfileValidation.isValid(profileRequestDTO);

        CustomUserDetails customUserDetails = new CustomUserDetails();
        ProfileEntity profile = get(customUserDetails.getId());
        return ApiResponse.success(ProfileResponseDTO.toDTO(profileRepository.save(profile)));
    }
}
