package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentLikeRequestDTO;
import kun.uz.dto.response.CommentLikeResponseDTO;
import kun.uz.service.ArticleService;
import kun.uz.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/like")
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/create")
    public CommentLikeResponseDTO create(@Valid @RequestBody CommentLikeRequestDTO commentLikeRequestDTO) {
        return commentLikeService.create(commentLikeRequestDTO);
    }
    @PutMapping("/update")
    public CommentLikeResponseDTO update(@Valid @RequestBody CommentLikeRequestDTO commentLikeRequestDTO) {
        return commentLikeService.update(commentLikeRequestDTO);
    }
    @GetMapping("/get/{id}")
    public CommentLikeResponseDTO get(@PathVariable("id") String id) {
        return commentLikeService.getById(id);
    }
    @GetMapping("/getAll")
    public List<CommentLikeResponseDTO> getAll() {
        return commentLikeService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return commentLikeService.delete(id);
    }
}
