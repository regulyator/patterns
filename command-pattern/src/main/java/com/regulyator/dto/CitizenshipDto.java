package com.regulyator.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CitizenshipDto {
    @NotNull
    private final String countryCode;
}
