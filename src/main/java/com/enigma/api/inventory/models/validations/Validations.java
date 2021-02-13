package com.enigma.api.inventory.models.validations;

import com.enigma.api.inventory.exceptions.EntityNotFoundException;

public class Validations {

    public static <T> void entityNotNull(T entity) {
        if (entity == null) throw new EntityNotFoundException();
    }
}
