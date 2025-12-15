package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentRequestDTO;
import kun.uz.dto.response.CommentResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class CommentService {
    public CommentResponseDTO create(@Valid CommentRequestDTO commentRequestDTO) {
        return null;
    }

    public CommentResponseDTO update(@Valid CommentRequestDTO commentRequestDTO) {
        return null;
    }

    public CommentResponseDTO getById(String id) {
        return null;
    }

    public List<CommentResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
