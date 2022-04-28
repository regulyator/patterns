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
@Table("Citizen")
public class Citizen implements StorageEntity {
    @Id
    private long id;
    private String name;
    private boolean hasFreedom;
    private int incomeValue;
}
