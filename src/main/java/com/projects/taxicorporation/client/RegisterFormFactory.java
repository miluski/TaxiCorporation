package com.projects.taxicorporation.client;

/**
 * Wzorzec projektowy fabryka abstrakcyjna
 */
public class RegisterFormFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return new RegisterForm();
    }
}
