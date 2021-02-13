package com.enigma.api.inventory.models.request.item;

import com.enigma.api.inventory.models.pagination.PageSearch;
import com.enigma.api.inventory.models.validations.alphabetic.Alphabetic;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearch extends PageSearch {

    @Alphabetic
    private String name;
}
