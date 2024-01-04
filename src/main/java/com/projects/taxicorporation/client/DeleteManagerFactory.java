package com.projects.taxicorporation.client;

import java.util.Objects;

public class DeleteManagerFactory implements FormFactory {
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new DeleteManagerView() : new DeleteManagerDarkView();
    }
}
