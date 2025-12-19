package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.RegionRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.RegionResponseDTO;
import kun.uz.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/region")
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/create")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ApiResponse<RegionResponseDTO> create(@Valid @RequestBody RegionRequestDTO regionRequestDTO) {
        return regionService.create(regionRequestDTO);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public ApiResponse<RegionResponseDTO> update(@RequestParam("id") String id ,@Valid @RequestBody RegionRequestDTO regionRequestDTO) {
        return regionService.update(id, regionRequestDTO);
    }
    @GetMapping("/get/{id}")
    public ApiResponse<RegionResponseDTO> get(@PathVariable("id") String id) {
        return regionService.getById(id);
    }

    @GetMapping("/getAll")
    public ApiResponse<List<RegionResponseDTO>> getAll() {
        return regionService.getAll();
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    public Boolean delete(@PathVariable String id) {
        return regionService.delete(id);
    }
}
