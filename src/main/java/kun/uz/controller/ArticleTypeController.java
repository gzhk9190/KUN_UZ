package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleTypeResponseDTO;
import kun.uz.service.ArticleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/type")
public class ArticleTypeController {
    private final ArticleTypeService articleTypeService;
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ApiResponse<ArticleTypeResponseDTO> create(@Valid @RequestBody ArticleTypeRequestDTO articleTypeRequestDTO) {
        return articleTypeService.create(articleTypeRequestDTO);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ApiResponse<ArticleTypeResponseDTO> update(@PathVariable("id")String id,@Valid @RequestBody ArticleTypeRequestDTO articleTypeRequestDTO) {
        return articleTypeService.update(id,articleTypeRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ApiResponse<ArticleTypeResponseDTO> get(@PathVariable("id") String id) {
        return articleTypeService.getById(id);
    }
    @GetMapping("/getAll")
    public ApiResponse<List<ArticleTypeResponseDTO>> getAll() {
        return articleTypeService.getAll();
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return articleTypeService.delete(id);
    }
}
