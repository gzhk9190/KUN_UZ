package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.dto.response.ProfileResponseDTO;
import kun.uz.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/create")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ProfileResponseDTO create(@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        return profileService.create(profileRequestDTO);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ProfileResponseDTO update(@PathVariable("id")String id,@Valid @RequestBody ProfileRequestDTO profileRequestDTO) {
        return profileService.update(id,profileRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ProfileResponseDTO get(@PathVariable("id") String id) {
        return profileService.getById(id);
    }
    @GetMapping("/getAll")
    public List<ProfileResponseDTO> getAll() {
        return profileService.getAll();
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return profileService.delete(id);
    }
}

