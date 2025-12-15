package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/create")
    public ProfileResponseDTO create(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        return profileService.create(profileRequestDTO);
    }
    @PutMapping("/update")
    public ProfileResponseDTO update(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        return profileService.update(profileRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ProfileResponseDTO get(@PathVariable("id") String id) {
        return profileService.getById(id);
    }
    @GetMapping("/getAll")
    public List<ProfileResponseDTO> getAll() {
        return profileService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return profileService.delete(id);
    }
}

