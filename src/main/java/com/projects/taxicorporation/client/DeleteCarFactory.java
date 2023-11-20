package com.projects.taxicorporation.client;

public class DeleteCarFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new DeleteCarView();
    }
}
