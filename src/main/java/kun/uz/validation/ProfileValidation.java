package kun.uz.validation;


import kun.uz.dto.request.ProfileRequestDTO;
import kun.uz.exp.AppBadRequestException;

public class ProfileValidation {

    public static void isValid (ProfileRequestDTO dto){
        if (dto.getName() == null || dto.getName().trim().isEmpty() || dto.getName().trim().length() < 3){
            throw new AppBadRequestException("Name Not Valid");
        }
        if (dto.getSurname() == null || dto.getSurname().trim().isEmpty() || dto.getSurname().trim().length() < 3){
            throw new AppBadRequestException("Surname Not Valid");
        }
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty() || dto.getEmail().trim().length() < 3 || !dto.getEmail().contains("@")){
            throw new AppBadRequestException("Email Not Valid");
        }
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty() || dto.getPassword().trim().length() < 8){
            throw new AppBadRequestException("Password Not Valid");
        }
        if (dto.getRole() == null){
            throw new AppBadRequestException("Role Not Valid");
        }
    }
}
