package com.projects.taxicorporation.client;

import java.util.Objects;

/**
 * Wzorzec projektowy fabryka abstrakcyjna
 */
public class RegisterFormFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new RegisterFormView() : new RegisterFormDarkView();
    }
}
