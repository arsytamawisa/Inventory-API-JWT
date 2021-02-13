package com.enigma.api.inventory.models.request.unit;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter @Setter
public class UnitPostRequest {

    @NotBlank
    @Size(min = 1, max = 5)
    private String code;

    @NotBlank
    private String description;
}
