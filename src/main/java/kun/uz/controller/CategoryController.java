package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.CategoryRequestDTO;
import kun.uz.dto.response.CategoryResponseDTO;
import kun.uz.service.ArticleService;
import kun.uz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/create")
    public CategoryResponseDTO create(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.create(categoryRequestDTO);
    }
    @PutMapping("/update")
    public CategoryResponseDTO update(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.update(categoryRequestDTO);
    }
    @GetMapping("/get/{id}")
    public CategoryResponseDTO get(@PathVariable("id") String id) {
        return categoryService.getById(id);
    }
    @GetMapping("/getAll")
    public List<CategoryResponseDTO> getAll() {
        return categoryService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return categoryService.delete(id);
    }
}
