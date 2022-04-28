package com.regulyator.service;

import com.regulyator.dto.CitizenDto;

public interface EntityService<E, ID> {
    E save(E e);
    E deleteById(ID id);
}
