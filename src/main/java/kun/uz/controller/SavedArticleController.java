package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.SavedArticleRequestDTO;
import kun.uz.dto.response.SavedArticleResponseDTO;
import kun.uz.service.SavedArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/saved/article")
public class SavedArticleController {
    private final SavedArticleService savedArticleService;
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<SavedArticleResponseDTO> create(@Valid @RequestBody SavedArticleRequestDTO savedArticleRequestDTO) {
        return ResponseEntity.ok(savedArticleService.create(savedArticleRequestDTO));
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public SavedArticleResponseDTO update(@PathVariable("Id")String id,@Valid @RequestBody SavedArticleRequestDTO savedArticleRequestDTO) {
        return savedArticleService.update(id,savedArticleRequestDTO);
    }
    @GetMapping("/get/{id}")
    public SavedArticleResponseDTO get(@PathVariable("id") String id) {
        return savedArticleService.getById(id);
    }
    @GetMapping("/getAll")
    public List<SavedArticleResponseDTO> getAll() {

        return savedArticleService.getAll();
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return savedArticleService.delete(id);
    }
}
