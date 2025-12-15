package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.RegionRequestDTO;
import kun.uz.dto.response.RegionResponseDTO;
import kun.uz.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/region")
public class RegionController {
    private final RegionService regionService;

    @PostMapping("/create")
    public RegionResponseDTO create(@Valid @RequestBody RegionRequestDTO regionRequestDTO) {
        return regionService.create(regionRequestDTO);
    }
    @PutMapping("/update")
    public RegionResponseDTO update(@Valid @RequestBody RegionRequestDTO regionRequestDTO) {
        return regionService.update(regionRequestDTO);
    }
    @GetMapping("/get/{id}")
    public RegionResponseDTO get(@PathVariable("id") String id) {
        return regionService.getById(id);
    }
    @GetMapping("/getAll")
    public List<RegionResponseDTO> getAll() {
        return regionService.getAll();
    }
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return regionService.delete(id);
    }
}
