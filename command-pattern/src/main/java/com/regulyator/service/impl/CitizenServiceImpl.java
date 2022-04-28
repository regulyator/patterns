package com.regulyator.service.impl;

import com.regulyator.entity.Citizen;
import com.regulyator.repository.CitizenRepository;
import com.regulyator.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CitizenServiceImpl implements EntityService<Citizen, Long> {
    private final CitizenRepository citizenRepository;

    @Override
    public Citizen save(Citizen citizen) {
        return citizenRepository.save(citizen);
    }

    @Override
    public Citizen deleteById(Long id) {
        var citizen = citizenRepository.findById(id);
        citizen.ifPresent(c -> citizenRepository.deleteById(c.getId()));
        return citizen.orElse(null);
    }
}
