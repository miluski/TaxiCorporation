package com.projects.taxicorporation.server;

public abstract class Task {
    public abstract void sendRequest();
    public abstract void returnFeedback(String content);
}
