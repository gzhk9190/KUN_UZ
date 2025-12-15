package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentRequestDTO;
import kun.uz.dto.response.CommentResponseDTO;
import kun.uz.service.ArticleService;
import kun.uz.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/main")
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/create")
    public CommentResponseDTO create(@Valid @RequestBody CommentRequestDTO commentRequestDTO  ) {
        return commentService.create(commentRequestDTO);
    }
    @PutMapping("/update")
    public CommentResponseDTO update(@Valid @RequestBody CommentRequestDTO commentRequestDTO  ) {
        return commentService.update(commentRequestDTO);
    }
    @GetMapping("/get/{id}")
    public CommentResponseDTO get(@PathVariable("id") String id) {
        return commentService.getById(id);
    }
    @GetMapping("/getAll")
    public List<CommentResponseDTO> getAll() {
        return commentService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return commentService.delete(id);
    }
}
