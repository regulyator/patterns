package com.regulyator.service;

public interface EntityService<E, ID> {
    E save(E e);

    E deleteById(ID id);
}
