package com.projects.taxicorporation.client;

import java.util.Objects;

public class AddDriverFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new AddDriverView() : new AddDriverDarkView();
    }
}
