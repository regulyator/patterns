package com.regulyator.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CitizenDto {
    @NotNull
    private final String name;
}
