package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.dto.response.ArticleResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ArticleService {
    public ArticleResponseDTO create(ArticleRequestDTO articleRequestDTO) {
        return null;
    }

    public ArticleResponseDTO update(@Valid ArticleRequestDTO articleRequestDTO) {
        return null;
    }

    public ArticleResponseDTO getById(String id) {
return null;
    }

    public List<ArticleResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
