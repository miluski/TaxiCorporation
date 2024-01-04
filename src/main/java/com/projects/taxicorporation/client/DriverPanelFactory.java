package com.projects.taxicorporation.client;

import java.util.Objects;

public class DriverPanelFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new DriverPanelView() : new DriverPanelDarkView();
    }
}
