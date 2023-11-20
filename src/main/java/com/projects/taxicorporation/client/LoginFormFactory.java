package com.projects.taxicorporation.client;

public class LoginFormFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return new LoginFormView();
    }
}
