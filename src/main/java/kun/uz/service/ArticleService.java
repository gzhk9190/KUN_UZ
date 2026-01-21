package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.config.details.EntityDetails;
import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleResponseDTO;
import kun.uz.entities.ArticleEntity;
import kun.uz.enums.ArticleStatus;
import kun.uz.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final AttachService attachService;
    private final RegionService regionService;
    private final CategoryService categoryService;

    public ApiResponse<?> create(ArticleRequestDTO dto) {
        if (Objects.nonNull(dto.getImageId())) {
            attachService.get(dto.getImageId());
        }

        if (Objects.nonNull(dto.getRegionId())) {
            ApiResponse<?> region = regionService.getById(dto.getRegionId());
            if (region.getIsError()) {
                return region;
            }
        }

        if (Objects.nonNull(dto.getCategoryId())) {
            ApiResponse<?> category = categoryService.getById(dto.getCategoryId());
            if (category.getIsError()) {
                return category;
            }
        }

        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setModeratorId(EntityDetails.getId());
        entity.setSharedCount(0);
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        ArticleEntity saved = articleRepository.save(entity);
        return ApiResponse.success(ArticleResponseDTO.toDTO(saved));
    }

    public ArticleResponseDTO update(String id, @Valid ArticleRequestDTO articleRequestDTO) {
        return null;
    }

    public ArticleResponseDTO getById(String id) {
return null;
    }

    public List<ArticleResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {

        return false;
    }

    public ApiResponse<Boolean> updateStatus(String id, ArticleStatus status) {
       int r = articleRepository.updateStatus(id, status, EntityDetails.getId());
       return r > 0 ? ApiResponse.success(true) : ApiResponse.badRequest("Qandaydir xato bo'ldi shekil");
    }
}

