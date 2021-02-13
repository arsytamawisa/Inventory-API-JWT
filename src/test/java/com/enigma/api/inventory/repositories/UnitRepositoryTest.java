package com.enigma.api.inventory.repositories;

import com.enigma.api.inventory.entities.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UnitRepositoryTest {

    @Autowired
    private UnitRepository repository;

    @Test
    void shouldInsertData() {
        Unit expected = new Unit();
        expected.setCode("test");
        expected.setDescription("test");

        repository.save(expected);
        Unit actual = repository.findById(expected.getId()).get();
        Assertions.assertEquals(expected, actual);
    }
}
