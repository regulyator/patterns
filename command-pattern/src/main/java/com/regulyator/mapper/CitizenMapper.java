package com.regulyator.mapper;

import com.regulyator.dto.CitizenDto;
import com.regulyator.entity.Citizen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitizenMapper {
    Citizen fromDto(CitizenDto citizenDto);
    CitizenDto toDto(Citizen citizen);
}
