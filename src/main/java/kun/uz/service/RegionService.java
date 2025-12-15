package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.RegionRequestDTO;
import kun.uz.dto.response.RegionResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class RegionService {
    public RegionResponseDTO create(@Valid RegionRequestDTO regionRequestDTO) {
        return null;
    }

    public RegionResponseDTO update(@Valid RegionRequestDTO regionRequestDTO) {
        return null;
    }

    public RegionResponseDTO getById(String id) {
        return null;
    }

    public List<RegionResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
