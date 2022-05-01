package com.regulyator.service.command;

import com.regulyator.entity.StorageEntity;

public interface CommandRunner<T extends StorageEntity> {

    T execute(DaoCommand<? extends StorageEntity> e);

    boolean undo();
}
