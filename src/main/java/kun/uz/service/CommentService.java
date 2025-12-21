package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.CommentResponseDTO;
import kun.uz.entities.CommentEntity;
import kun.uz.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class CommentService {
    private final CommentRepository repository;
    private final ProfileService pService;
    private final ArticleService aService;
    public ApiResponse<CommentResponseDTO> create(@Valid CommentRequestDTO dto) {
    if (Objects.isNull(dto.getContent())) {
        return ApiResponse.badRequest("Content mavjud emas");
    }
    if (dto.getContent().length() <= 200) {
        return ApiResponse.badRequest("Content 200 belgidan oshiq uzunikda bo'lmasligi shart!");
    }
    if (Objects.isNull(pService.getById(dto.getProfileId()))) {
        return ApiResponse.badRequest("Bunday profile_id li Profile mavjud emas");
    }
    if (Objects.isNull(aService.getById(dto.getArticleId()))) {
        return ApiResponse.badRequest("Bunday article_id li Article mavjud emas");
    }
    CommentEntity entity = new CommentEntity();
    entity.setContent(dto.getContent());
    entity.setProfileId(dto.getProfileId());
    entity.setArticleId(dto.getArticleId());
    entity.setReplyId(dto.getReplyId());
    entity.setUpdatedDate(dto.getUpdatedDate());
    CommentEntity saved = repository.save(entity);
    return ApiResponse.success(CommentResponseDTO.toDTO(saved));
}

    public ApiResponse<CommentResponseDTO> update(String id, @Valid CommentRequestDTO dto) {
        if (Objects.isNull(dto.getContent())) {
            return ApiResponse.badRequest("Content mavjud emas");
        }
        if (dto.getContent().length() <= 200) {
            return ApiResponse.badRequest("Content 200 belgidan oshiq uzunikda bo'lmasligi shart!");
        }
        if (Objects.isNull(pService.getById(dto.getProfileId()))) {
            return ApiResponse.badRequest("Bunday profile_id li Profile mavjud emas");
        }
        if (Objects.isNull(aService.getById(dto.getArticleId()))) {
            return ApiResponse.badRequest("Bunday article_id li Article mavjud emas");
        }

        CommentEntity entity = get(id);
        entity.setContent(dto.getContent());
        entity.setProfileId(dto.getProfileId());
        entity.setArticleId(dto.getArticleId());
        entity.setReplyId(dto.getReplyId());
        entity.setUpdatedDate(dto.getUpdatedDate());
        CommentEntity saved = repository.save(entity);
        return ApiResponse.success(CommentResponseDTO.toDTO(saved));
    }

    public ApiResponse<CommentResponseDTO> getById(String id) {
        Optional<CommentEntity> optional = repository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday profile mavjud emas");
        } else {
            return ApiResponse.success(CommentResponseDTO.toDTO(optional.get()));
        }

    }

    private CommentEntity get(String id) {
        return repository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<CommentResponseDTO>> getAll() {
        return ApiResponse.success(repository.findAllByVisibleIsTrue().stream().map(CommentResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        repository.updateVisible(id);
        return true;
    }
}
