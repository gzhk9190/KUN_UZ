package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.config.details.CustomUserDetails;
import kun.uz.config.details.EntityDetails;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import kun.uz.entities.ArticleLikeEntity;
import kun.uz.enums.ProfileRole;
import kun.uz.repository.ArticleLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ArticleLikeService {

    private final ArticleLikeRepository repository;
    private final ProfileService pService;
    private final ArticleService aService;
    public ApiResponse<ArticleLikeResponseDTO> create(@Valid ArticleLikeRequestDTO dto) {

        if (Objects.isNull(aService.getById(dto.getArticleId()))) {
            return ApiResponse.badRequest("Bunday  id li article mavjud emas");
        }
        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(EntityDetails.getId());
        entity.setStatus(dto.getStatus());
        ArticleLikeEntity saved = repository.save(entity);
        return ApiResponse.success(ArticleLikeResponseDTO.toDTO(saved));
    }

    public ApiResponse<ArticleLikeResponseDTO> update(String id, @Valid ArticleLikeRequestDTO dto) {

        ArticleLikeEntity entity = get(id);
        entity.setStatus(dto.getStatus());
        ArticleLikeEntity saved = repository.save(entity);
        return ApiResponse.success(ArticleLikeResponseDTO.toDTO(saved));
    }

    public ApiResponse<ArticleLikeResponseDTO> getById(String id) {
        Optional<ArticleLikeEntity> optional = repository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday ArticleLike mavjud emas");
        } else {
            return ApiResponse.success(ArticleLikeResponseDTO.toDTO(optional.get()));
        }

    }

    private ArticleLikeEntity get(String id) {
        return repository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<ArticleLikeResponseDTO>> getAll() {
        return ApiResponse.success(repository.findAllByVisibleIsTrue().stream().map(ArticleLikeResponseDTO::toDTO).toList());
    }

    public ApiResponse<Boolean> delete(String id) {
        if (EntityDetails.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            repository.updateVisible(id);
        } else {
            ArticleLikeEntity entity = get(id);
            if (Objects.nonNull(entity)) {
                if (entity.getProfileId().equals(EntityDetails.getId())) {
                    repository.updateVisible(id);
                } else {
                    return ApiResponse.forbidden("Bu likeni o'chirish uchun huquqingiz yo'q");
                }
            }
        }
        return ApiResponse.success(true);
    }
}
