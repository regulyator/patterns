package com.regulyator.service.impl;

import com.regulyator.entity.Housing;
import com.regulyator.exception.CitizenCreationException;
import com.regulyator.repository.HousingRepository;
import com.regulyator.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HousingServiceImpl implements EntityService<Housing, Long> {
    //as example for rollback test
    private static final String FORBIDDEN_HOUSING = "forbidden address";
    private final HousingRepository housingRepository;

    @Override
    public Housing save(Housing housing) {
        if (housing.getAddress().equalsIgnoreCase(FORBIDDEN_HOUSING)) {
            throw new CitizenCreationException("Forbidden address code!");
        }
        return housingRepository.save(housing);
    }

    @Override
    public Housing deleteById(Long id) {
        var housing = housingRepository.findById(id);
        housing.ifPresent(h -> housingRepository.deleteById(h.getId()));
        return housing.orElse(null);
    }
}
