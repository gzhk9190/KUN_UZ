package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.CategoryRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.CategoryResponseDTO;
import kun.uz.entities.CategoryEntity;
import kun.uz.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class CategoryService {
    private final CategoryRepository repository;
    public ApiResponse<CategoryResponseDTO> create(@Valid CategoryRequestDTO dto) {
        if (Objects.isNull(dto.getOrderNumber())) {
            return ApiResponse.badRequest("Order number null bo'lmasligi shart!");
        }
        Optional<CategoryEntity> optional = repository.findByNameUzAndVisibleIsTrue(dto.getNameUz());
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday category mavjud");
        }
        Optional<CategoryEntity> optional1 = repository.findByNameEnAndVisibleIsTrue(dto.getNameEn());
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Category Exists");
        }
        Optional<CategoryEntity> optional2 = repository.findByNameRuAndVisibleIsTrue(dto.getNameRu());
        if (optional2.isPresent()) {
            return ApiResponse.badRequest("Category Exists(ru)");
        }
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        CategoryEntity saved = repository.save(entity);
        return ApiResponse.success(CategoryResponseDTO.toDTO(saved));
    }

    public ApiResponse<CategoryResponseDTO> update(String id, @Valid CategoryRequestDTO dto) {
        if (Objects.isNull(dto.getOrderNumber())) {
            return ApiResponse.badRequest("Order number null bo'lmasligi shart!");
        }
        Optional<CategoryEntity> optional = repository.findByNameUzAndVisibleIsTrue(dto.getNameUz());
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday category mavjud");
        }
        Optional<CategoryEntity> optional1 = repository.findByNameEnAndVisibleIsTrue(dto.getNameEn());
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Category Exists");
        }
        Optional<CategoryEntity> optional2 = repository.findByNameRuAndVisibleIsTrue(dto.getNameRu());
        if (optional2.isPresent()) {
            return ApiResponse.badRequest("Category Exists(ru)");
        }
        CategoryEntity entity = get(id);
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        CategoryEntity saved = repository.save(entity);
        return ApiResponse.success(CategoryResponseDTO.toDTO(saved));
    }

    public ApiResponse<CategoryResponseDTO> getById(String id) {
        Optional<CategoryEntity> optional = repository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday category mavjud emas");
        } else {
            return ApiResponse.success(CategoryResponseDTO.toDTO(optional.get()));
        }

    }

    private CategoryEntity get(String id) {
        return repository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<CategoryResponseDTO>> getAll() {
        return ApiResponse.success(repository.findAllByVisibleIsTrue().stream().map(CategoryResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        repository.updateVisible(id);
        return true;
    }
}
