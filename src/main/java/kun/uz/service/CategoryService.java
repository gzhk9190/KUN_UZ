package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.CategoryRequestDTO;
import kun.uz.dto.response.CategoryResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class CategoryService {
    public CategoryResponseDTO create(@Valid CategoryRequestDTO categoryRequestDTO) {
        return null;
    }

    public CategoryResponseDTO update(@Valid CategoryRequestDTO categoryRequestDTO) {
        return  null;
    }

    public CategoryResponseDTO getById(String id) {
        return null;
    }

    public List<CategoryResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
