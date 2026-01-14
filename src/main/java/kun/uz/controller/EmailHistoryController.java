package kun.uz.controller;

import kun.uz.dto.response.ApiResponse;

import kun.uz.dto.response.EmailHistoryResponseDTO;
import kun.uz.entities.EmailHistoryEntity;
import kun.uz.service.EmailHistoryService;
import kun.uz.service.EmailSMSService;
import kun.uz.service.ProfileService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email/history")
public class EmailHistoryController {
    private final EmailHistoryService emailHistoryService;

    @GetMapping("/get/{eid}")
    public ApiResponse<List<EmailHistoryResponseDTO>>getEmailHistoryByEId(@PathVariable("eid") String eId) {
        return emailHistoryService.getById(eId);
    }

    @GetMapping("/get/by/{given_date}")
    public ApiResponse<List<EmailHistoryResponseDTO>> getByGivenDate(@PathVariable("given_date") LocalDate givenDate) {
        return emailHistoryService.getByGivenDate(givenDate);
    }

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @GetMapping("/pagination")
    public ResponseEntity<Page<EmailHistoryResponseDTO>> getPaginationList(Pageable pageable) {
        return ResponseEntity.ok(emailHistoryService.getPagination(pageable));
    }

}
