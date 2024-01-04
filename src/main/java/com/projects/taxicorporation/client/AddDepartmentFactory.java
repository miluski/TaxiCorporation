package com.projects.taxicorporation.client;

import java.util.Objects;

public class AddDepartmentFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new AddDepartmentView() : new AddDepartmentDarkView();
    }
}
