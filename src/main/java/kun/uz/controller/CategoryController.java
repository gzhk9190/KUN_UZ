package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.CategoryRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.CategoryResponseDTO;
import kun.uz.service.ArticleService;
import kun.uz.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {
    private final CategoryService categoryService;
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ApiResponse<CategoryResponseDTO> create(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.create(categoryRequestDTO);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ApiResponse<CategoryResponseDTO> update(@PathVariable("id")String id,@Valid @RequestBody CategoryRequestDTO categoryRequestDTO) {
        return categoryService.update(id,categoryRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ApiResponse<CategoryResponseDTO> get(@PathVariable("id") String id) {
        return categoryService.getById(id);
    }
    @GetMapping("/getAll")
    public ApiResponse<List<CategoryResponseDTO>> getAll() {
        return categoryService.getAll();
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return categoryService.delete(id);
    }
}
