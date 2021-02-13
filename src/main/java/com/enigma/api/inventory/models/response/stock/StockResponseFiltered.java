package com.enigma.api.inventory.models.response.stock;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockResponseFiltered {
    private Integer id;
    private String quantity;
    private Integer totalPrice;
}
