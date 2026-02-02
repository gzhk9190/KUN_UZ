package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.config.details.EntityDetails;
import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleResponseDTO;
import kun.uz.entities.ArticleEntity;
import kun.uz.enums.ArticleStatus;
import kun.uz.repository.ArticleRepository;
import kun.uz.repository.ArticleTypeRepository;
import kun.uz.validation.ArticleValidation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ArticleTypeRepository articleTypeRepository;
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
        ArticleValidation.isValid(dto);

        ArticleEntity entity = getArticle(dto);
        ArticleEntity saved = articleRepository.save(entity);
        return ApiResponse.success(ArticleResponseDTO.toDTO(saved));
    }

    private static ArticleEntity getArticle(ArticleRequestDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setImageId(dto.getImageId());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setModeratorId(EntityDetails.getId());
        entity.setSharedCount(0);
        entity.setViewCount(0);
        entity.setStatus(ArticleStatus.NOT_PUBLISHED);
        return entity;
    }

    public ApiResponse<?> update(String id, @Valid ArticleRequestDTO dto) {
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

        return ApiResponse.success(ArticleResponseDTO.toDTO(articleRepository.save(getArticle(dto))));
    }

    public ApiResponse<ArticleResponseDTO> getById(String id) {
        Optional<ArticleEntity> optional = articleRepository.findByIdAndVisibleIsTrue(id);
        return optional.map(profileEntity -> ApiResponse.success(ArticleResponseDTO.toDTO(profileEntity))).orElseGet(() -> ApiResponse.badRequest("Bunday profile mavjud emas"));

    }

    private ArticleEntity get(String id) {
        return articleRepository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<ArticleResponseDTO>> getAll() {
        return ApiResponse.success(
                articleRepository.findAllByVisibleIsTrue()
                        .stream()
                        .map(this::toDTO)
                        .toList()
        );
    }


    public ApiResponse<Boolean> delete(String id) {
        articleRepository.updateVisible(id);
        return ApiResponse.success(true);
    }

    public ApiResponse<Boolean> updateStatus(String id, ArticleStatus status) {
        int r = articleRepository.updateStatus(id, status, EntityDetails.getId());
        return r > 0 ? ApiResponse.success(true) : ApiResponse.badRequest("Qandaydir xato bo'ldi shekil");
    }

    public ApiResponse<?> getFiveByType(ArticleTypeRequestDTO type) {
        Pageable limitFive = PageRequest.of(0, 5);

        List<ArticleEntity> articles = articleRepository.findLast5ByType(
                type.getNameUz(),
                limitFive
        );

        List<ArticleResponseDTO> responseList = articles.stream()
                .map(this::toDTO)
                .toList();

        return ApiResponse.success(responseList);
    }

    private ArticleResponseDTO toDTO(ArticleEntity articleEntity) {
        return ArticleResponseDTO.toDTO(articleEntity);
    }

    public ApiResponse<?> getThreeByType(ArticleTypeRequestDTO type) {
    Pageable limitThree = PageRequest.of(0, 3);

        List<ArticleEntity> articles = articleRepository.findLast3ByType(
                type.getNameUz(),
                limitThree
        );
        List<ArticleResponseDTO> responseDTOs = articles.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());

        return ApiResponse.success(responseDTOs);
    }

    public Object getEightByIdIsNot(String[] ids) {
        return ApiResponse.success(articleRepository.getAllWhichAreNot(ids).stream().map(this::toDTO));
    }

    public ApiResponse<List<ArticleResponseDTO>> getFourByTypeAndExceptId(
            String exceptId,
            ArticleTypeRequestDTO dto
    ) {
        String typeId = articleTypeRepository
                .getByNameUz(dto.getNameUz())
                .getId();

        List<ArticleResponseDTO> result =
                articleRepository.findTop4ByTypeExceptId(
                                typeId,
                                exceptId,
                                PageRequest.of(0, 4)
                        ).stream()
                        .map(this::toDTO)
                        .toList();
        return ApiResponse.success(result);
    }

    public Object getMostReadArticles() {
        return ApiResponse.success(articleRepository.mostread4().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public Object last5ByRegionKeyAndType(String regionkey, ArticleTypeRequestDTO articleTypeRequestDTO) {
        return ApiResponse.success(articleRepository.last5ByRegionKeyAndType(regionkey, articleTypeRequestDTO, PageRequest.of(0, 5)).stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public Object last5ByRegionKeyAndType(String regionkey, ArticleTypeRequestDTO articleTypeRequestDTO, Pageable pageable) {
        return ApiResponse.success(articleRepository.last5ByRegionKeyAndType(regionkey, articleTypeRequestDTO, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())).stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public Object last5ByCategoryId(String categorykey) {
        return ApiResponse.success(articleRepository.last5ByCategoryId(categorykey, PageRequest.of(0, 5)).stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public Object last5ByCategoryIdPageable(String categorykey, Pageable pageable) {
        return ApiResponse.success(articleRepository.last5ByCategoryId(categorykey, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort())).stream().map(ArticleResponseDTO::toDTO).collect(Collectors.toList()));
    }

    public Object incView(String articleId) {
        return ApiResponse.success(articleRepository.incViewCount(articleId));
    }

    public Object incShare(String articleId) {
        return ApiResponse.success(articleRepository.incShareCount(articleId));
    }
}

