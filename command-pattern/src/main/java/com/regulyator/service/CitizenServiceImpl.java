package com.regulyator.service;

import com.regulyator.dto.CitizenDto;
import com.regulyator.mapper.CitizenMapper;
import com.regulyator.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitizenServiceImpl implements CitizenService {

    private final CitizenRepository citizenRepository;
    private final CitizenMapper citizenMapper;

    @Override
    public long save(CitizenDto citizenDto) {
        var id = citizenRepository.save(citizenMapper.fromDto(citizenDto)).getId();
        return id;
    }
}
