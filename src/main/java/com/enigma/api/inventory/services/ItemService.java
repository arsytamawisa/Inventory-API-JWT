package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Item;
import com.enigma.api.inventory.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractService<Item, Integer> {

    @Autowired
    protected ItemService(ItemRepository repository) {
        super(repository);
    }
}
