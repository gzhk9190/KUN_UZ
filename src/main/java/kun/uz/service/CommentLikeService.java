package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentLikeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.CommentLikeResponseDTO;
import kun.uz.entities.CommentLikeEntity;
import kun.uz.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor()
@Slf4j
public class CommentLikeService {
    private final CommentLikeRepository repository;
    private final ProfileService profileService;
    private final CommentService commentService;
    public ApiResponse<CommentLikeResponseDTO> create(@Valid CommentLikeRequestDTO dto) {

        if (Objects.isNull(profileService.getById(dto.getProfileId()))) {
            return ApiResponse.badRequest("Bunday  Profile_id li profile mavjud emas");
        }
        if (Objects.isNull(commentService.getById(dto.getCommentId()))) {
            return ApiResponse.badRequest("Bunday id Comment_id li comment mavjud emas");
        }
        CommentLikeEntity entity = new CommentLikeEntity();
        entity.setCommentId(dto.getCommentId());
        entity.setProfileId(dto.getProfileId());
        entity.setStatus(dto.getStatus());
        CommentLikeEntity saved = repository.save(entity);
        return ApiResponse.success(CommentLikeResponseDTO.toDTO(saved));
    }

    public ApiResponse<CommentLikeResponseDTO> update(String id, @Valid CommentLikeRequestDTO dto) {
        if (Objects.isNull(profileService.getById(dto.getProfileId()))) {
            return ApiResponse.badRequest("Bunday  Profile_id li profile mavjud emas");
        }
        if (Objects.isNull(commentService.getById(dto.getCommentId()))) {
            return ApiResponse.badRequest("Bunday id Comment_id li comment mavjud emas");
        }
        CommentLikeEntity entity = get(id);
        entity.setCommentId(dto.getCommentId());
        entity.setProfileId(dto.getProfileId());
        entity.setStatus(dto.getStatus());
        CommentLikeEntity saved = repository.save(entity);
        return ApiResponse.success(CommentLikeResponseDTO.toDTO(saved));
    }

    public ApiResponse<CommentLikeResponseDTO> getById(String id) {
        Optional<CommentLikeEntity> optional = repository.findByIdAndVisibleIsTrue(id);
        if (optional.isEmpty()) {
            return ApiResponse.badRequest("Bunday commentLike mavjud emas");
        } else {
            return ApiResponse.success(CommentLikeResponseDTO.toDTO(optional.get()));
        }

    }

    private CommentLikeEntity get(String id) {
        return repository.findByIdAndVisibleIsTrue(id).orElse(null);
    }

    public ApiResponse<List<CommentLikeResponseDTO>> getAll() {
        return ApiResponse.success(repository.findAllByVisibleIsTrue().stream().map(CommentLikeResponseDTO::toDTO).toList());
    }

    public Boolean delete(String id) {
        repository.updateVisible(id);
        return true;
    }
}
