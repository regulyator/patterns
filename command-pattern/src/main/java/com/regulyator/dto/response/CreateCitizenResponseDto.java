package com.regulyator.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateCitizenResponseDto {
    private final long idCitizenCreated;
    private final boolean success;
}
