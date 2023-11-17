package com.projects.taxicorporation.server;

import java.util.List;

public abstract class Task {
    public abstract void sendRequest();
    public abstract void returnFeedback(List<String> retrievedData);
}
