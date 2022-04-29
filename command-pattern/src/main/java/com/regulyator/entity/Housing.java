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
@Table("Housing")
public class Housing implements StorageEntity {
    @Id
    private long id;
    private long idCitizen;
    private String address;
}
