package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleLikeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleLikeResponseDTO;
import kun.uz.service.ArticleLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/like")
public class ArticleLikeController {
    private final ArticleLikeService articleLikeService;

    @PostMapping("/create")
    public ApiResponse<ArticleLikeResponseDTO> create(@Valid @RequestBody ArticleLikeRequestDTO articleLikeRequestDTO) {
        return articleLikeService.create(articleLikeRequestDTO);
    }

    @PutMapping("/update/{id}")
    public ApiResponse<ArticleLikeResponseDTO> update(@PathVariable("id")String id,@Valid @RequestBody ArticleLikeRequestDTO articleLikeRequestDTO) {
        return articleLikeService.update(id,articleLikeRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ApiResponse<ArticleLikeResponseDTO> get(@PathVariable("id") String id) {
        return articleLikeService.getById(id);
    }
    @GetMapping("/getAll")
    public ApiResponse<List<ArticleLikeResponseDTO>> getAll() {
        return articleLikeService.getAll();
    }

    @PutMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable String id) {
        return articleLikeService.delete(id);
    }
}
