package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class AddDriverCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        return new ArrayList<>();
    }
}
