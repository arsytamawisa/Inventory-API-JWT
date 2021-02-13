package com.enigma.api.inventory.models.validations.fileImage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileImageValidator.class)
@Documented
public @interface FileImage {
    String message() default "File Image";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
