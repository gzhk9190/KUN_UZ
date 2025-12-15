package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentLikeRequestDTO;
import kun.uz.dto.response.CommentLikeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class CommentLikeService {
    public CommentLikeResponseDTO create(@Valid CommentLikeRequestDTO commentLikeRequestDTO) {
        return null;
    }

    public CommentLikeResponseDTO update(@Valid CommentLikeRequestDTO commentLikeRequestDTO) {
        return null;
    }

    public CommentLikeResponseDTO getById(String id) {
        return null;
    }

    public List<CommentLikeResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
