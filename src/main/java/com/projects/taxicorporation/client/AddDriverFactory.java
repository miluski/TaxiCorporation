package com.projects.taxicorporation.client;

import java.util.Objects;

public class AddDriverFactory implements FormFactory {
    @Override
    public Form createForm() {
        System.out.println(MainStage.getInstance().getThemeName());
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new AddDriverView() : new AddDriverDarkView();
    }
}
