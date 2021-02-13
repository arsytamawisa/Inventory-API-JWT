package com.enigma.api.inventory.entities.summaries;

import com.enigma.api.inventory.entities.Item;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class StockSummary {
    private Item item;
    private Long totalQuantity;
    private Long totalPrice;
}
