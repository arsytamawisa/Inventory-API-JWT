package com.enigma.api.inventory.models.request.stock;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class StockRequest {

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer totalPrice;

    @NotNull
    private Integer itemId;
}
