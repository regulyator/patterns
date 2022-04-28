package com.regulyator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HousingDto {
    private final String address;
}
