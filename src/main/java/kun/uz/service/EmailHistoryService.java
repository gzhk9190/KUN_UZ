package kun.uz.service;


import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.CommentResponseDTO;
import kun.uz.dto.response.EmailHistoryResponseDTO;
import kun.uz.dto.response.EmailSMSResponseDTO;
import kun.uz.entities.EmailHistoryEntity;
import kun.uz.repository.EmailHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor()
@Slf4j
public class EmailHistoryService {
    private final EmailHistoryRepository emailHistoryRepository;

    public ApiResponse<List<EmailHistoryResponseDTO>> getById(String eId) {
        return ApiResponse.success(emailHistoryRepository.findAllByEmailId(eId).stream().map(EmailHistoryResponseDTO::toDTO).toList());
    }

    public ApiResponse<List<EmailHistoryResponseDTO>> getByGivenDate(LocalDate givenDate) {
        return ApiResponse.success(emailHistoryRepository.findAllByGivenDate(givenDate).stream().map(EmailHistoryResponseDTO::toDTO).toList());
    }

    public @Nullable Page<EmailHistoryResponseDTO> getPagination(Pageable pageable) {
        return emailHistoryRepository.findByVisible(true, pageable)
                .map(EmailHistoryResponseDTO::toDTO);
    }
}
