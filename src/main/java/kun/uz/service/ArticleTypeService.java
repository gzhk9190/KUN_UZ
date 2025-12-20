package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.dto.response.ArticleTypeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ArticleTypeService {
    public ArticleTypeResponseDTO create(@Valid ArticleTypeRequestDTO articleTypeRequestDTO) {
        return null;
    }

    public ArticleTypeResponseDTO update(String id, @Valid ArticleTypeRequestDTO articleTypeRequestDTO) {
        return null;
    }

    public ArticleTypeResponseDTO getById(String id) {
        return null;
    }

    public List<ArticleTypeResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
