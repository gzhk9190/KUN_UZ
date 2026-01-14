package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.RegionRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleTypeResponseDTO;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.dto.response.RegionResponseDTO;
import kun.uz.entities.ProfileEntity;
import kun.uz.entities.RegionEntity;
import kun.uz.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegionService {
    private final RegionRepository regionRepository;

    public ApiResponse<RegionResponseDTO> create(@Valid RegionRequestDTO dto) {
        Optional<RegionEntity> optional = regionRepository.findByNameUzAndVisibleIsTrue(dto.getNameUz());
        if (optional.isPresent()) {
            return ApiResponse.badRequest("Bunday region mavjud");
        }
        Optional<RegionEntity> optional1 = regionRepository.findByNameEnAndVisibleIsTrue(dto.getNameEn());
        if (optional1.isPresent()) {
            return ApiResponse.badRequest("Region Exists");
        }
        Optional<RegionEntity> optional2 = regionRepository.findByNameRuAndVisibleIsTrue(dto.getNameRu());
        if (optional2.isPresent()) {
            return ApiResponse.badRequest("Region Exists(ru)");
        }
        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setOrderNumber(dto.getOrderNumber());
        RegionEntity saved = regionRepository.save(entity);

        return ApiResponse.success(RegionResponseDTO.toDTO(saved));
    }

    public ApiResponse<RegionResponseDTO> update(String id, @Valid RegionRequestDTO regionRequestDTO) {
        RegionEntity entity = get(id);
        if (Objects.isNull(entity)) {
            return ApiResponse.badRequest("Topilmadi");
        }
        entity.setNameUz(regionRequestDTO.getNameUz());
        entity.setNameEn(regionRequestDTO.getNameEn());
        entity.setNameRu(regionRequestDTO.getNameRu());
        entity.setOrderNumber(regionRequestDTO.getOrderNumber());
        return ApiResponse.success(RegionResponseDTO.toDTO(regionRepository.save(entity)));
        // TODO update qiling
    }

    public ApiResponse<RegionResponseDTO> getById(String id) {
        Optional<RegionEntity> optional = regionRepository.findByIdAndVisibleIsTrue(id);
        if(optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday viloyat yoki shahar mavjud emas");
        } else {
            return ApiResponse.success(RegionResponseDTO.toDTO(optional.get()));
        }

     }

     private RegionEntity get(String id) {
        return regionRepository.findByIdAndVisibleIsTrue(id).orElse(null);
     }

    public ApiResponse<List<RegionResponseDTO>> getAll() {
        return ApiResponse.success(regionRepository.findAllByVisibleIsTrue().stream().map(RegionResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        regionRepository.updateVisible(id);
        return true;
    }

    public @Nullable Page<RegionResponseDTO> getPagination(Pageable pageable) {
        return regionRepository.findByVisible(true, pageable).map(this::toDTO);
    }
    public RegionResponseDTO toDTO(RegionEntity entity) {
        return RegionResponseDTO.toDTO(entity);
    }

    public ApiResponse<List<RegionResponseDTO>> getByLang(String lang) {
        switch (lang){
            case "en"->{
                return ApiResponse.success(regionRepository.findAllByVisibleIsTrueAndNameEnIsNotNull().stream().map(RegionResponseDTO::toDTO).toList());
            }case "uz"->{
                return ApiResponse.success(regionRepository.findAllByVisibleIsTrueAndNameUzIsNotNull().stream().map(RegionResponseDTO::toDTO).toList());
            }case "ru"->{
                return ApiResponse.success(regionRepository.findAllByVisibleIsTrueAndNameRuIsNotNull().stream().map(RegionResponseDTO::toDTO).toList());
            }
        }
        return ApiResponse.badRequest("Language not exists");
    }
}
