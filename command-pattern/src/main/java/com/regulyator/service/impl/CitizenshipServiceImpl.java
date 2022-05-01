package com.regulyator.service.impl;

import com.regulyator.entity.Citizenship;
import com.regulyator.exception.CitizenCreationException;
import com.regulyator.repository.CitizenshipRepository;
import com.regulyator.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitizenshipServiceImpl implements EntityService<Citizenship, Long> {
    //as example for rollback test
    private static final String FORBIDDEN_COUNTRY_CODE = "forbidden code";
    private final CitizenshipRepository citizenshipRepository;

    @Override
    public Citizenship save(Citizenship citizenship) {
        if (citizenship.getCountryCode().equalsIgnoreCase(FORBIDDEN_COUNTRY_CODE)) {
            throw new CitizenCreationException("Forbidden country code!");
        }
        return citizenshipRepository.save(citizenship);
    }

    @Override
    public Citizenship deleteById(Long id) {
        var citizenship = citizenshipRepository.findById(id);
        citizenship.ifPresent(c -> citizenshipRepository.deleteById(c.getId()));
        return citizenship.orElse(null);
    }
}
