package com.enigma.api.inventory.controllers.defaults;

import com.enigma.api.inventory.services.FileService;
import com.enigma.api.inventory.services.ItemService;
import com.enigma.api.inventory.services.StockService;
import com.enigma.api.inventory.services.UnitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class Components {

    @Autowired
    protected ItemService itemService;

    @Autowired
    protected UnitService unitService;

    @Autowired
    protected StockService stockService;

    @Autowired
    protected ModelMapper modelMapper;

    @Autowired
    protected FileService fileService;

}
