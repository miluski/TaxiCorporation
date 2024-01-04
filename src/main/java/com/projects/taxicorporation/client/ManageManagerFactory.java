package com.projects.taxicorporation.client;

import java.util.Objects;

public class ManageManagerFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new ManageManagerView() : new ManageManagerDarkView();
    }
}
