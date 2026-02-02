package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ArticleRequestDTO;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.ArticleResponseDTO;
import kun.uz.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/article/main")

public class ArticleController {
    private final ArticleService articleService;

    @PreAuthorize(value = "hasAnyRole( 'ROLE_MODERATOR')")
    @PostMapping("/create")
    public ApiResponse<?> create(@Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.create(articleRequestDTO);
    }

    @PreAuthorize(value = "hasAnyRole( 'ROLE_MODERATOR')")
    @PutMapping("/update/{id}")
    public ApiResponse<?> update(@PathVariable("id") String id, @Valid @RequestBody ArticleRequestDTO articleRequestDTO) {
        return articleService.update(id, articleRequestDTO);
    }

    @GetMapping("/get/{id}")
    public ApiResponse<ArticleResponseDTO> get(@PathVariable("id") String id) {
        return articleService.getById(id);
    }

    @GetMapping("/getAll")
    public ApiResponse<List<ArticleResponseDTO>> getAll() {
        return articleService.getAll();
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize(value = "hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR')")
    public ApiResponse<Boolean> delete(@PathVariable String id) {
        return articleService.delete(id);
    }

    @PostMapping("/last5ByType")
    public ApiResponse<?> getLastFiveArticles(@RequestBody ArticleTypeRequestDTO type) {
        return ApiResponse.success(articleService.getFiveByType(type));
    }

    @PostMapping("/last3ByType")
    public ApiResponse<?> getLastThreeArticles(@RequestBody ArticleTypeRequestDTO type) {
        return ApiResponse.success(articleService.getThreeByType(type));
    }

    @GetMapping("/last8WhereIdIsNot/{ids}")
    public ApiResponse<?> get8WhereIdIsNot(@PathVariable("ids") String[] ids) {
        return ApiResponse.success(articleService.getEightByIdIsNot(ids));
    }

    @PostMapping("/last4/except/{id}")
    public ApiResponse<?> getByArticleTypeWhereIdIsNot(@PathVariable String id, @RequestBody() ArticleTypeRequestDTO articleTypeRequestDTO) {
        return ApiResponse.success(articleService.getFourByTypeAndExceptId(id, articleTypeRequestDTO));
    }

    @GetMapping("/mostr4/")
    public ApiResponse<?> getMostRead4Articles() {
        return ApiResponse.success(articleService.getMostReadArticles());
    }

    @PostMapping("/last5/{regionkey}")
    public ApiResponse<?> getLast5ArticlesByType(@PathVariable("regionkey") String regionkey, ArticleTypeRequestDTO articleTypeRequestDTO) {
        return ApiResponse.success(articleService.last5ByRegionKeyAndType(regionkey, articleTypeRequestDTO));
    }

    @PostMapping("/pagination/last5/{regionkey}")
    public ApiResponse<Object> getLast5ArticlesByTypePagination(@PathVariable("regionkey") String regionkey, ArticleTypeRequestDTO articleTypeRequestDTO, Pageable pageable) {
        return ApiResponse.success(articleService.last5ByRegionKeyAndType(regionkey, articleTypeRequestDTO, pageable));
    }

    @GetMapping("/last5/{categorykey}")
    public ApiResponse<Object> getLast5ByCategoryId(@PathVariable("categorykey") String categorykey) {
        return ApiResponse.success(articleService.last5ByCategoryId(categorykey));
    }

    @GetMapping("/pagination/last5/{categorykey}")
    public ApiResponse<Object> getLast5ByCategoryIdPagination(@PathVariable("categorykey") String categorykey, Pageable pageable) {
        return ApiResponse.success(articleService.last5ByCategoryIdPageable(categorykey, pageable));
    }

    @PutMapping("/view+/{articleId}")
    public ApiResponse<?> view(@PathVariable("articleId") String articleId) {
        return ApiResponse.success(articleService.incView(articleId));
    }

    @PutMapping("/share+/{articleId}")
    public ApiResponse<?> share(@PathVariable("articleId") String articleId) {
        return ApiResponse.success(articleService.incShare(articleId));
    }
}
