package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.SavedArticleRequestDTO;
import kun.uz.dto.response.SavedArticleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class SavedArticleService {
    public SavedArticleResponseDTO create(@Valid SavedArticleRequestDTO savedArticleRequestDTO) {
        return null;
    }

    public SavedArticleResponseDTO update(String id, @Valid SavedArticleRequestDTO savedArticleRequestDTO) {
        return null;
    }

    public SavedArticleResponseDTO getById(String id) {
        return null;
    }

    public List<SavedArticleResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
