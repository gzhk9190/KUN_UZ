package kun.uz.validation;

import kun.uz.dto.request.CategoryRequestDTO;
import kun.uz.exp.AppBadRequestException;

public class CategoryValidation {
    public static void isValid(CategoryRequestDTO dto){
        if (dto.getNameEn() == null || dto.getNameEn().trim().length() < 2) {
            throw new AppBadRequestException("NameEn not valid");
        }
        if (dto.getNameRu() == null || dto.getNameRu().trim().length() < 2) {
            throw new AppBadRequestException("NameRu not valid");
        }
        if (dto.getNameUz() == null || dto.getNameUz().trim().length() < 2) {
            throw new AppBadRequestException("NameUz not valid");
        }
    }
}
