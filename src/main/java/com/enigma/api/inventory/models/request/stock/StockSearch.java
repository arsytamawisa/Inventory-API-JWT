package com.enigma.api.inventory.models.request.stock;

import com.enigma.api.inventory.models.pagination.PageSearch;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StockSearch extends PageSearch {
    private Integer quantity;
}
