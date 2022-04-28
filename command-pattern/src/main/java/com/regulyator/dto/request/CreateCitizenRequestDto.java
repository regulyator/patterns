package com.regulyator.dto.request;

import com.regulyator.dto.CitizenDto;
import com.regulyator.dto.CitizenshipDto;
import com.regulyator.dto.HousingDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CreateCitizenRequestDto {
    private final CitizenDto citizen;
    private final List<CitizenshipDto> citizenShips;
    private final List<HousingDto> housings;
}
