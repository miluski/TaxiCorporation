package com.projects.taxicorporation.client;

public class DeleteManagerFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new DeleteManagerView();
    }
}
