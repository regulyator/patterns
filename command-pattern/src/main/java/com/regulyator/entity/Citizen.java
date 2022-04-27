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
public class Citizen implements StorageEntity{
    @Id
    private final long id;
    private final String name;
    private boolean hasFreedom;
    private long idIncome;
}
