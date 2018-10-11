package com.epam.jd12.repository;

import com.epam.jd12.specification.Specification;

import java.util.List;
import java.util.Map;

public interface Repository<T> {

    void add(T item);

    void remove(T item);

    void update(T item);

    Map<Integer, T> getAll();

    List<T> query(Specification<T> specification);
}
