package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.CommentRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.CommentResponseDTO;
import kun.uz.service.ArticleService;
import kun.uz.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment/main")
public class CommentController {
    private final CommentService commentService;
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ApiResponse<CommentResponseDTO> create(@Valid @RequestBody CommentRequestDTO commentRequestDTO  ) {
        return commentService.create(commentRequestDTO);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ApiResponse<CommentResponseDTO> update(@PathVariable("id")String id,@Valid @RequestBody CommentRequestDTO commentRequestDTO  ) {
        return commentService.update(id,commentRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ApiResponse<CommentResponseDTO> get(@PathVariable("id") String id) {
        return commentService.getById(id);
    }
    @GetMapping("/getAll")
    public ApiResponse<List<CommentResponseDTO>> getAll() {
        return commentService.getAll();
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return commentService.delete(id);
    }
}
