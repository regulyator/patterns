package com.regulyator.service.command;

import com.regulyator.entity.Citizen;
import com.regulyator.service.EntityService;
import lombok.AllArgsConstructor;

import java.util.Objects;

@AllArgsConstructor
public class CitizenSaveCommand implements DaoCommand<Citizen> {
    private final EntityService<Citizen, Long> citizenService;
    private Citizen citizen;

    @Override
    public Citizen save(Citizen citizen) {
        this.citizen = citizenService.save(citizen);
        return this.citizen;
    }

    @Override
    public void rollBack() {
        if(Objects.nonNull(citizen) && citizen.getId() != 0) {
            citizenService.deleteById(citizen.getId());
        }
    }
}
