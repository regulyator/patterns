package com.regulyator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CitizenshipDto {
    private final String countryCode;
}
