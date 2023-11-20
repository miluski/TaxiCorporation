package com.projects.taxicorporation.client;

public class AddCarFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return new AddCarView();
    }
}
