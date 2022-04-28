package com.regulyator.service.command;

import com.regulyator.entity.Housing;

public class HousingSaveCommand implements DaoCommand<Housing> {
    @Override
    public Housing save(Housing housing) {
        return null;
    }

    @Override
    public void rollBack() {

    }
}
