package com.epam.jd12.specification;

public interface Specification<T> {

    boolean specified(T item);
}
