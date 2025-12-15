package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import kun.uz.service.ArticleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/like")
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @PostMapping("/create")
    public ArticleLikeResponseDTO create(@Valid @RequestBody ArticleLikeRequestDTO articleLikeRequestDTO) {
        return articleLikeService.create(articleLikeRequestDTO);
    }
    @PutMapping("/update")
    public ArticleLikeResponseDTO update(@Valid @RequestBody ArticleLikeRequestDTO articleLikeRequestDTO) {
        return articleLikeService.update(articleLikeRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ArticleLikeResponseDTO get(@PathVariable("id") String id) {
        return articleLikeService.getById(id);
    }
    @GetMapping("/getAll")
    public List<ArticleLikeResponseDTO> getAll() {
        return articleLikeService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return articleLikeService.delete(id);
    }
}
