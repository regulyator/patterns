package com.regulyator.service;

import com.regulyator.dto.request.CreateCitizenRequestDto;
import com.regulyator.dto.response.CreateCitizenResponseDto;

public interface CitizenApiService {

    CreateCitizenResponseDto createCitizen(CreateCitizenRequestDto createCitizenRequestDto);
}
