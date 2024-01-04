package com.projects.taxicorporation.client;

import java.util.Objects;

public class DeleteCarFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new DeleteCarView() : new DeleteCarDarkView();
    }
}
