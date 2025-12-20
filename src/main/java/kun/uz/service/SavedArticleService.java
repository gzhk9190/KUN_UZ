package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.SavedArticleRequestDTO;
import kun.uz.dto.request.SavedArticleRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.SavedArticleResponseDTO;
import kun.uz.dto.response.SavedArticleResponseDTO;
import kun.uz.entities.SavedArticleEntity;
import kun.uz.repository.SavedArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class SavedArticleService {

    private final SavedArticleRepository savedArticleRepo;

    public ApiResponse<SavedArticleResponseDTO> create(@Valid SavedArticleRequestDTO dto) {
        Optional<SavedArticleEntity> optional = savedArticleRepo.findByProfileIdAndVisibleIsTrue((dto.getProfileId()));
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday id li profile yo'q-ku");
        }
        Optional<SavedArticleEntity>optional1=savedArticleRepo.findByArticleIdAndVisibleIsTrue(dto.getArticleId());
        if (optional1.isEmpty()){
            return ApiResponse.badRequest("Bunday  id li article yo'q-ku");
        }

        SavedArticleEntity entity = new SavedArticleEntity();
        entity.setArticleId(dto.getArticleId());
        entity.setProfileId(dto.getProfileId());
        SavedArticleEntity saved = savedArticleRepo.save(entity);

        return ApiResponse.success(SavedArticleResponseDTO.toDTO(saved));
    }

    public ApiResponse<SavedArticleResponseDTO> update(String id, @Valid SavedArticleRequestDTO regionRequestDTO) {
        SavedArticleEntity entity = get(id);
        if (Objects.isNull(entity)) {
            return ApiResponse.badRequest("Topilmadi");
        }
        entity.setNameUz(regionRequestDTO.getNameUz());
        entity.setNameEn(regionRequestDTO.getNameEn());
        entity.setNameRu(regionRequestDTO.getNameRu());
        entity.setOrderNumber(regionRequestDTO.getOrderNumber());
        return ApiResponse.success(SavedArticleResponseDTO.toDTO(savedArticleRepo.save(entity)));
        // TODO update qiling
    }

    public ApiResponse<SavedArticleResponseDTO> getById(String id) {
        Optional<SavedArticleEntity> optional = savedArticleRepo.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday viloyat yoki shahar mavjud emas");
        } else {
            return ApiResponse.success(SavedArticleResponseDTO.toDTO(optional.get()));
        }

    }

    private SavedArticleEntity get(String id) {
        return savedArticleRepo.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<SavedArticleResponseDTO>> getAll() {
        return ApiResponse.success(savedArticleRepo.findAllByVisibleIsTrue().stream().map(SavedArticleResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        savedArticleRepo.updateVisible(id);
        return true;
    }
}
