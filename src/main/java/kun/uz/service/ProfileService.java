package kun.uz.service;

import jakarta.validation.Valid;
import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.dto.response.ProfileResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class ProfileService {
    public ProfileResponseDTO create(@Valid ProfileRequestDTO profileRequestDTO) {
        return null;
    }

    public ProfileResponseDTO update(@Valid ProfileRequestDTO profileRequestDTO) {
        return null;
    }

    public ProfileResponseDTO getById(String id) {
        return null;
    }

    public List<ProfileResponseDTO> getAll() {
        return null;
    }

    public Boolean delete(String id) {
        return false;
    }
}
