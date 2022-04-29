package com.regulyator.service.command.impl;

import com.regulyator.entity.StorageEntity;
import com.regulyator.service.command.CommandRunner;
import com.regulyator.service.command.DaoCommand;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Objects;

public class CommandRunnerImpl implements CommandRunner<StorageEntity> {
    private final Deque<DaoCommand<? extends StorageEntity>> commands = new ArrayDeque<>();

    @Override
    public StorageEntity execute(DaoCommand<? extends StorageEntity> command) {
        var storageEntity = command.save();
        if (Objects.nonNull(storageEntity)) {
            commands.push(command);
            return storageEntity;
        }
        return null;
    }

    @Override
    public boolean undo() {
        if(commands.size() > 0) {
            commands.poll().rollBack();
            return true;
        }
        return false;
    }
}
