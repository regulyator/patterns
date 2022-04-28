package com.regulyator.service;

import com.regulyator.dto.CitizenDto;
import com.regulyator.dto.request.CreateCitizenRequestDto;

public interface CitizenApiService {

    boolean createCitizen(CreateCitizenRequestDto createCitizenRequestDto);
}
