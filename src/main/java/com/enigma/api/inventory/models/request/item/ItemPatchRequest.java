package com.enigma.api.inventory.models.request.item;
import com.enigma.api.inventory.models.validations.alphabetic.Alphabetic;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ItemPatchRequest {

    @Alphabetic
    private String name;

    private Integer price;

    private Integer unitId;
}
