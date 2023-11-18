package com.projects.taxicorporation.client;

public class ClientPanelFactory implements FormFactory {
    /**
     * Wzorzec projektowy metoda wytwórcza
     */
    @Override
    public Form createForm() {
        return new ClientPanelForm();
    }
}
