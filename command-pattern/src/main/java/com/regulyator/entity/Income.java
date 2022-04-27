package com.regulyator.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@RequiredArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Table("Income")
public class Income implements StorageEntity{
    @Id
    private final long id;
    private final int value;
}
