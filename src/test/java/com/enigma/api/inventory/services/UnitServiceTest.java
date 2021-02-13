package com.enigma.api.inventory.services;

import com.enigma.api.inventory.entities.Unit;
import com.enigma.api.inventory.repositories.UnitRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UnitServiceTest {

    @InjectMocks
    private UnitService service;

    @Mock
    private UnitRepository repository;

    @Test
    public void shouldInsertData() {
        Unit input = new Unit();
        input.setCode("test");
        input.setDescription("test");

        Unit output = new Unit();
        output.setId(1);
        output.setCode("test");
        output.setDescription("test");

        Mockito.when(repository.save(Mockito.any())).thenReturn(output);
        Unit result = service.save(input);
        Assertions.assertEquals(output, result);
    }
}
