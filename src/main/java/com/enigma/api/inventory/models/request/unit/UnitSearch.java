package com.enigma.api.inventory.models.request.unit;

import com.enigma.api.inventory.models.pagination.PageSearch;
import com.enigma.api.inventory.models.validations.alphabetic.Alphabetic;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UnitSearch extends PageSearch {

    @Alphabetic
    private String code;
}
