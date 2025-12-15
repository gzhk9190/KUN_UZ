package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ArticleLikeService {

    public ArticleLikeResponseDTO create(@Valid ArticleLikeRequestDTO articleLikeRequestDTO) {
        return null;
    }

    public ArticleLikeResponseDTO update(@Valid ArticleLikeRequestDTO articleRequestDTO) {
        return null;
    }

    public ArticleLikeResponseDTO getById(String id) {
        return null;
    }

    public List<ArticleLikeResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
