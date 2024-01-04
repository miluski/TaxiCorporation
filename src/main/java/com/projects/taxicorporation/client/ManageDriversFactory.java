package com.projects.taxicorporation.client;

import java.util.Objects;

public class ManageDriversFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new ManageDriversView() : new ManageDriversDarkView();
    }
}
