package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import kun.uz.entities.ArticleLikeEntity;
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
        if (Objects.isNull(pService.getById(dto.getProfileId()))) {
            return ApiResponse.badRequest("Bunday id li profile mavjud emas");
        }
        if (Objects.isNull(aService.getById(dto.getArticleId()))) {
            return ApiResponse.badRequest("Bunday  id li article mavjud emas");
        }
        ArticleLikeEntity entity = new ArticleLikeEntity();
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(dto.getProfileId());
        entity.setStatus(dto.getStatus());
        ArticleLikeEntity saved = repository.save(entity);
        return ApiResponse.success(ArticleLikeResponseDTO.toDTO(saved));
    }

    public ApiResponse<ArticleLikeResponseDTO> update(String id, @Valid ArticleLikeRequestDTO dto) {

        ArticleLikeEntity entity = get(id);
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(dto.getProfileId());
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

    public Boolean delete(String id) {
        repository.updateVisible(id);
        return true;
    }
}
