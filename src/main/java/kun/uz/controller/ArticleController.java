package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.dto.response.ArticleResponseDTO;
import kun.uz.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/main")
public class ArticleController {
    private final ArticleService articleService;

    @PostMapping("/create")
    public ArticleResponseDTO create(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.create(articleRequestDTO);
    }
    @PutMapping("/update")
    public ArticleResponseDTO update(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.update(articleRequestDTO);
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
    public Boolean delete(@PathVariable String id) {
        return articleService.delete(id);
    }
}
