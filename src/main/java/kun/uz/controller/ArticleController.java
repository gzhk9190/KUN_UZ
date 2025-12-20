package kun.uz.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.Column;
import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.dto.response.ArticleResponseDTO;
import kun.uz.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/main")

public class ArticleController {
    private final ArticleService articleService;
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ArticleResponseDTO create(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.create(articleRequestDTO);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update")
    public ArticleResponseDTO update(@PathVariable("id")String id,@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.update(id,articleRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ArticleResponseDTO get(@PathVariable("id") String id) {
        return articleService.getById(id);
    }
    @GetMapping("/getAll")
    public List<ArticleResponseDTO> getAll() {
        return articleService.getAll();
    }
    @PutMapping("/delete/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public Boolean delete(@PathVariable String id) {
        return articleService.delete(id);
    }
}
