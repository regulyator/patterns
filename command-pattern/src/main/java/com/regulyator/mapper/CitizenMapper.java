package com.regulyator.mapper;

import com.regulyator.dto.CitizenDto;
import com.regulyator.entity.Citizen;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitizenMapper extends DtoMapper<Citizen, CitizenDto> {
}
