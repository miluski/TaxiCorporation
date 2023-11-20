package com.projects.taxicorporation.client;

public class ManageManagerFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new ManageManagerView();
    }
}
