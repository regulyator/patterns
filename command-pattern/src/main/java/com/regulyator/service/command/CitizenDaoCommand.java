package com.regulyator.service.command;

import com.regulyator.entity.Citizen;

public class CitizenDaoCommand implements DaoCommand<Citizen> {
    private long id;
    @Override
    public Citizen save(Citizen entity) {
        this.id = entity.getId();
        return null;
    }

    @Override
    public void rollBack() {

    }
}
