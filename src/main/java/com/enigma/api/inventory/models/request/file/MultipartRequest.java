package com.enigma.api.inventory.models.request.file;

import com.enigma.api.inventory.models.validations.fileImage.FileImage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter @Setter
public class MultipartRequest {

    @FileImage
    private MultipartFile file;
}
