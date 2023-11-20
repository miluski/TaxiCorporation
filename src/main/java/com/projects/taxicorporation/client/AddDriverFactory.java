package com.projects.taxicorporation.client;

public class AddDriverFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new AddDriverView();
    }
}
