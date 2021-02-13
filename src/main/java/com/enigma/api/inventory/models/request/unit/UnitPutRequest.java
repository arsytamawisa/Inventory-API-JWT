package com.enigma.api.inventory.models.request.unit;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Getter @Setter
public class UnitPutRequest {

    @NotBlank
    private String description;
}
