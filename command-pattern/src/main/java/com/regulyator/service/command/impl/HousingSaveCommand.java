package com.regulyator.service.command.impl;

import com.regulyator.entity.Housing;
import com.regulyator.service.EntityService;
import com.regulyator.service.command.DaoCommand;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class HousingSaveCommand implements DaoCommand<Housing> {
    private final EntityService<Housing, Long> housingService;
    @NonNull
    private Housing housing;

    @Override
    public Housing save() {
        housing = housingService.save(housing);
        return housing;
    }

    @Override
    public void rollBack() {
        if (housing.getId() != 0) {
            housingService.deleteById(housing.getId());
        }
    }
}
