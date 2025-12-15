package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.SavedArticleRequestDTO;
import kun.uz.dto.response.SavedArticleResponseDTO;
import kun.uz.service.SavedArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saved/article")
public class SavedArticleController {
    private final SavedArticleService savedArticleService;

    @PostMapping("/create")
    public ResponseEntity<SavedArticleResponseDTO> create(@Valid @RequestBody SavedArticleRequestDTO savedArticleRequestDTO) {
        return ResponseEntity.ok(savedArticleService.create(savedArticleRequestDTO));
    }
    @PutMapping("/update")
    public SavedArticleResponseDTO update(@Valid @RequestBody SavedArticleRequestDTO savedArticleRequestDTO) {
        return savedArticleService.update(savedArticleRequestDTO);
    }
    @GetMapping("/get/{id}")
    public SavedArticleResponseDTO get(@PathVariable("id") String id) {
        return savedArticleService.getById(id);
    }
    @GetMapping("/getAll")
    public List<SavedArticleResponseDTO> getAll() {
        return savedArticleService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return savedArticleService.delete(id);
    }
}
