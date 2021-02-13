package com.enigma.api.inventory.models.response.stock;
import com.enigma.api.inventory.models.response.item.ItemResponse;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class StockResponse {
    private Integer id;
    private Integer quantity;
    private Integer totalPrice;
    private ItemResponse item;
}
