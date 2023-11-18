package com.projects.taxicorporation.client;

public class AddDepartmentFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new AddDepartment();
    }
}
