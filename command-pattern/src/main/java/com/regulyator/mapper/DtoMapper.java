package com.regulyator.mapper;

public interface DtoMapper<E, D> {
    E fromDto(D dto);

    D toDto(E e);
}
