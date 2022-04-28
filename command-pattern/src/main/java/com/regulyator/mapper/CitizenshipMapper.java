package com.regulyator.mapper;

import com.regulyator.dto.CitizenshipDto;
import com.regulyator.entity.Citizenship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CitizenshipMapper extends DtoMapper<Citizenship, CitizenshipDto> {
}
