package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.EmailSMSRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.EmailSMSResponseDTO;
import kun.uz.entities.EmailSMSEntity;
import kun.uz.repository.EmailSMSRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailSMSService {
    private final EmailSMSRepository repository;
    public ApiResponse<EmailSMSResponseDTO> create(@Valid EmailSMSRequestDTO dto) {
        EmailSMSEntity entity = new EmailSMSEntity();
        entity.setCode(dto.getCode());
        entity.setEmail(dto.getEmail());
        entity.setUsed(dto.getUsed());
        EmailSMSEntity saved = repository.save(entity);
        return ApiResponse.success(EmailSMSResponseDTO.toDTO(saved));
    }

    public ApiResponse<EmailSMSResponseDTO> update(String id, @Valid EmailSMSRequestDTO dto) {
        EmailSMSEntity entity = get(id);
        entity.setCode(dto.getCode());
        entity.setEmail(dto.getEmail());
        entity.setUsed(dto.getUsed());
        EmailSMSEntity saved = repository.save(entity);
        return ApiResponse.success(EmailSMSResponseDTO.toDTO(saved));
    }

    public ApiResponse<EmailSMSResponseDTO> getById(String id) {
        Optional<EmailSMSEntity> optional = repository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday category mavjud emas");
        } else {
            return ApiResponse.success(EmailSMSResponseDTO.toDTO(optional.get()));
        }

    }

    private EmailSMSEntity get(String id) {
        return repository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<EmailSMSResponseDTO>> getAll() {
        return ApiResponse.success(repository.findAllByVisibleIsTrue().stream().map(EmailSMSResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        repository.updateVisible(id);
        return true;
    }
}
