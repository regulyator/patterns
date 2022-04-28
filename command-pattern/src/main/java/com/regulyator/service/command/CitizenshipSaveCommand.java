package com.regulyator.service.command;

import com.regulyator.entity.Citizenship;

public class CitizenshipSaveCommand implements DaoCommand<Citizenship> {
    @Override
    public Citizenship save(Citizenship citizenship) {
        return null;
    }

    @Override
    public void rollBack() {

    }
}
