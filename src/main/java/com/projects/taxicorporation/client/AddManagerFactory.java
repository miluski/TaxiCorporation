package com.projects.taxicorporation.client;

public class AddManagerFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new AddManagerView();
    }
}
