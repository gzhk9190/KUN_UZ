package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.dto.response.ArticleTypeResponseDTO;
import kun.uz.service.ArticleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/type")
public class ArticleTypeController {
    private final ArticleTypeService articleTypeService;

    @PostMapping("/create")
    public ArticleTypeResponseDTO create(@Valid @RequestBody ArticleTypeRequestDTO articleTypeRequestDTO) {
        return articleTypeService.create(articleTypeRequestDTO);
    }
    @PutMapping("/update")
    public ArticleTypeResponseDTO update(@Valid @RequestBody ArticleTypeRequestDTO articleTypeRequestDTO) {
        return articleTypeService.update(articleTypeRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ArticleTypeResponseDTO get(@PathVariable("id") String id) {
        return articleTypeService.getById(id);
    }
    @GetMapping("/getAll")
    public List<ArticleTypeResponseDTO> getAll() {
        return articleTypeService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return articleTypeService.delete(id);
    }
}
