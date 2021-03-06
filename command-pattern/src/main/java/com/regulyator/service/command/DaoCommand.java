package com.regulyator.service.command;

import com.regulyator.entity.StorageEntity;

public interface DaoCommand<E extends StorageEntity> {
    E save();

    void rollBack();
}
