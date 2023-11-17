package com.projects.taxicorporation.server;

import java.sql.Connection;
import java.util.List;

public class LogoutCommand implements Command {
    @Override
    public List<String> execute(List<String> data, Connection connect) {
        return null;
    }
}
