package com.regulyator.dto.request;

import com.regulyator.dto.*;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class CreateCitizenRequestDto {
    @NotNull
    private final CitizenDto citizen;
    @NotEmpty
    private final List<CitizenshipDto> citizenShips;
    @NotNull
    private final Boolean freedom;
    @NotEmpty
    private final List<HousingDto> housings;
    @NotNull
    private final IncomeDto income;
}
