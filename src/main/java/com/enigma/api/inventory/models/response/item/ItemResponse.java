package com.enigma.api.inventory.models.response.item;
import com.enigma.api.inventory.models.response.unit.UnitResponse;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponse {
    private Integer id;
    private String name;
    private Integer price;
    private UnitResponse unit;
}
