package com.projects.taxicorporation.server;

import java.util.List;

public class ConcreteCommunicateMediator implements CommunicateMediator {
    private final Command command;
    private final AbstractDataBase dataBase;
    public ConcreteCommunicateMediator(Command command, AbstractDataBase database) {
        this.command = command;
        this.dataBase = database;
    }
    @Override
    public List<String> mediate(List<String> data) {
        return this.dataBase.execute(command, data);
    }
}
