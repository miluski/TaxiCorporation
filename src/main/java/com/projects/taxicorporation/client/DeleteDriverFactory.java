package com.projects.taxicorporation.client;

public class DeleteDriverFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new DeleteDriver();
    }
}
