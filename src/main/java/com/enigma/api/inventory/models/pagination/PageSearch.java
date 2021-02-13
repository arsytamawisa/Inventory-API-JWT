package com.enigma.api.inventory.models.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import javax.validation.constraints.Max;

@Getter @Setter
public class PageSearch {

    @Max(100)
    private Integer size = 10;
    private Integer page = 0;
    private Sort.Direction sort = Sort.Direction.ASC;
}
