package com.enigma.api.inventory.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import java.util.List;

public interface Service<T, ID> {

    T save(T entity);
    T findById(ID id);
    List<T> findAll();
    Page<T> findAll(T search, int page, int size, Sort.Direction direction);
    T removeById(ID id);

}
