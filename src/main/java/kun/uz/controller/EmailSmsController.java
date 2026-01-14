package kun.uz.controller;

import jakarta.validation.Valid;
import kun.uz.dto.request.EmailSMSRequestDTO;
import kun.uz.dto.response.ApiResponse;
import kun.uz.dto.response.EmailSMSResponseDTO;
import kun.uz.service.EmailSMSService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email/sms")
public class EmailSmsController {
    private final EmailSMSService service;

    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ApiResponse<EmailSMSResponseDTO> create(@Valid @RequestBody EmailSMSRequestDTO request) {
        return service.create(request);
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{id}")
    public ApiResponse<EmailSMSResponseDTO> update(@PathVariable("id")String id,@Valid @RequestBody EmailSMSRequestDTO request) {
        return service.update(id,request);
    }
    @GetMapping("/get/{id}")
    public ApiResponse<EmailSMSResponseDTO> get(@PathVariable("id") String id) {
        return service.getById(id);
    }
    @GetMapping("/getAll")
    public ApiResponse<List<EmailSMSResponseDTO>> getAll() {
        return service.getAll();
    }
    @PreAuthorize(value = "hasRole('ROLE_ADMIN')")
    @PutMapping("/delete/{id}")
    public Boolean delete(@PathVariable String id) {
        return service.delete(id);
    }


}
