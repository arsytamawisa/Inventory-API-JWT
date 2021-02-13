package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repositories.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitService extends AbstractService<Unit, Integer> {

    @Autowired
    protected UnitService(UnitRepository repository) {
        super(repository);
    }
}
