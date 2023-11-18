package com.projects.taxicorporation.client;

public class ChooseRoadFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return new ChooseRoadForm();
    }
}
