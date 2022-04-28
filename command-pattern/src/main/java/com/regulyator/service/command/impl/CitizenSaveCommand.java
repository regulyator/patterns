package com.regulyator.service.command.impl;

import com.regulyator.entity.Citizen;
import com.regulyator.service.EntityService;
import com.regulyator.service.command.DaoCommand;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CitizenSaveCommand implements DaoCommand<Citizen> {
    private final EntityService<Citizen, Long> citizenService;
    @NonNull
    private Citizen citizen;

    @Override
    public Citizen save() {
        citizen = citizenService.save(citizen);
        return this.citizen;
    }

    @Override
    public void rollBack() {
        if (citizen.getId() != 0) {
            citizenService.deleteById(citizen.getId());
        }
    }
}
