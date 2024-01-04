package com.projects.taxicorporation.client;

import java.util.Objects;

public class AddManagerFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new AddManagerView() : new AddManagerDarkView();
    }
}
