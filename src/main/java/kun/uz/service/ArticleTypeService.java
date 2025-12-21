package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleTypeResponseDTO;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.repository.ArticleTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ArticleTypeService {
    private final ArticleTypeRepository repository;
    public ApiResponse<ArticleTypeResponseDTO> create(@Valid ArticleTypeRequestDTO dto) {
        if (Objects.isNull(dto.getOrderNumber())) {
            return ApiResponse.badRequest("Order number null bo'lmasligi shart!");
        }
        Optional<ArticleTypeEntity> optional = repository.findByNameUzAndVisibleIsTrue(dto.getNameUz());
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday region mavjud");
        }
        Optional<ArticleTypeEntity> optional1 = repository.findByNameEnAndVisibleIsTrue(dto.getNameEn());
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Region Exists");
        }
        Optional<ArticleTypeEntity> optional2 = repository.findByNameRuAndVisibleIsTrue(dto.getNameRu());
        if (optional2.isPresent()) {
            return ApiResponse.badRequest("Region Exists(ru)");
        }
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        ArticleTypeEntity saved = repository.save(entity);
        return ApiResponse.success(ArticleTypeResponseDTO.toDTO(saved));
    }

    public ApiResponse<ArticleTypeResponseDTO> update(String id, @Valid ArticleTypeRequestDTO dto) {
        if (Objects.isNull(dto.getOrderNumber())) {
            return ApiResponse.badRequest("Order number null bo'lmasligi shart!");
        }
        Optional<ArticleTypeEntity> optional = repository.findByNameUzAndVisibleIsTrue(dto.getNameUz());
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday Article Type mavjud");
        }
        Optional<ArticleTypeEntity> optional1 = repository.findByNameEnAndVisibleIsTrue(dto.getNameEn());
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Article type Exists");
        }
        Optional<ArticleTypeEntity> optional2 = repository.findByNameRuAndVisibleIsTrue(dto.getNameRu());
        if (optional2.isPresent()) {
            return ApiResponse.badRequest("Article type Exists(ru)");
        }
        ArticleTypeEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        ArticleTypeEntity saved = repository.save(entity);
        return ApiResponse.success(ArticleTypeResponseDTO.toDTO(saved));
    }

    public ApiResponse<ArticleTypeResponseDTO> getById(String id) {
        Optional<ArticleTypeEntity> optional = repository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday ArticleType mavjud emas");
        } else {
            return ApiResponse.success(ArticleTypeResponseDTO.toDTO(optional.get()));
        }

    }

    private ArticleTypeEntity get(String id) {
        return repository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<ArticleTypeResponseDTO>> getAll() {
        return ApiResponse.success(repository.findAllByVisibleIsTrue().stream().map(ArticleTypeResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        repository.updateVisible(id);
        return true;
    }
}
