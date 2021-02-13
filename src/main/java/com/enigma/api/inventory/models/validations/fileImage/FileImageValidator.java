package com.enigma.api.inventory.models.validations.fileImage;

import lombok.SneakyThrows;
import org.apache.tika.Tika;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FileImageValidator implements ConstraintValidator<FileImage, MultipartFile> {

    @SneakyThrows
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext cvc) {
        if (file != null) {
            Tika tika = new Tika();
            return tika.detect(file.getInputStream()).startsWith("image/");
        }
        return true;
    }
}
