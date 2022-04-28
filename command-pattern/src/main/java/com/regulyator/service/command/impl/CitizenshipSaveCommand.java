package com.regulyator.service.command.impl;

import com.regulyator.entity.Citizenship;
import com.regulyator.service.EntityService;
import com.regulyator.service.command.DaoCommand;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class CitizenshipSaveCommand implements DaoCommand<Citizenship> {
    private final EntityService<Citizenship, Long> citizenshipService;
    @NonNull
    private Citizenship citizenship;

    @Override
    public Citizenship save() {
        citizenship = citizenshipService.save(citizenship);
        return this.citizenship;
    }

    @Override
    public void rollBack() {
        if (citizenship.getId() != 0) {
            citizenshipService.deleteById(citizenship.getId());
        }
    }
}
