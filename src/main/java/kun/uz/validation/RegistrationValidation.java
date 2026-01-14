package kun.uz.validation;

import kun.uz.dto.request.auth.RegistrationRequestDTO;
import kun.uz.exp.AppBadRequestException;

public class RegistrationValidation {
    public static void isValid (RegistrationRequestDTO dto) {
        if (dto.getName() == null || dto.getName().trim().isEmpty() || dto.getName().trim().length() < 3) {
            throw new AppBadRequestException("Name Not Valid");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().isEmpty() || dto.getSurname().trim().length() < 3) {
            throw new AppBadRequestException("Surname Not Valid");
        }
        if ((dto.getEmail() == null && dto.getPhone()==null) || (dto.getEmail().trim().isEmpty() && dto.getPhone().trim().isEmpty()) || (dto.getEmail().trim().length() < 3 || !dto.getEmail().contains("@")&& dto.getPhone().trim().length() < 5)) {
            throw new AppBadRequestException("Email or Phone Not Valid");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty() || dto.getPassword().trim().length() < 8) {
            throw new AppBadRequestException("Password Not Valid");
        }
    }

}
