package com.regulyator.mapper;

import com.regulyator.dto.HousingDto;
import com.regulyator.entity.Housing;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HousingMapper extends DtoMapper<Housing, HousingDto>{
}
