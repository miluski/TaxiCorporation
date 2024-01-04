package com.projects.taxicorporation.client;

import java.util.Objects;

public class AddCarFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new AddCarView() : new AddCarDarkView();
    }
}
