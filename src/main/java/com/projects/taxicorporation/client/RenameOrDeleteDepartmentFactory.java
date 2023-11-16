package com.projects.taxicorporation.client;

public class RenameOrDeleteDepartmentFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new RenameOrDeleteDepartment();
    }
}
