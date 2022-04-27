package com.regulyator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
@Builder
public class IncomeDto {
    @Min(value = 1000)
    private final int value;
}
