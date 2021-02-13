package com.enigma.api.inventory.models.response.item;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemResponseFiltered {
    private Integer id;
    private String name;
    private Integer price;
}
