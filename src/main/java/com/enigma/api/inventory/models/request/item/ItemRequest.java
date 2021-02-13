package com.enigma.api.inventory.models.request.item;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class ItemRequest {

    @NotBlank
    private String name;

    @NotNull
    private Integer price;

    @NotNull
    private Integer unitId;
}
