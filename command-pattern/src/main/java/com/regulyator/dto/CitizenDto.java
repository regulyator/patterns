package com.regulyator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenDto {
    private final String name;
    private Boolean hasFreedom;
    private Integer incomeValue;
}
