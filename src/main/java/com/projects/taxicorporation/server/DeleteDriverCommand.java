package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.util.List;

public class DeleteDriverCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        return null;
    }
}
