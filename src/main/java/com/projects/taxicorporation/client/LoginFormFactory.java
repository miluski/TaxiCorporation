package com.projects.taxicorporation.client;

import java.util.Objects;

public class LoginFormFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new LoginFormView() : new LoginFormDarkView();
    }
}
