package com.regulyator.service.impl;

import com.regulyator.entity.Housing;
import com.regulyator.repository.HousingRepository;
import com.regulyator.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HousingServiceImpl implements EntityService<Housing, Long> {
    private final HousingRepository housingRepository;

    @Override
    public Housing save(Housing housing) {
        return housingRepository.save(housing);
    }

    @Override
    public Housing deleteById(Long id) {
        var housing = housingRepository.findById(id);
        housing.ifPresent(h -> housingRepository.deleteById(h.getId()));
        return housing.orElse(null);
    }
}
