package com.projects.taxicorporation.client;

public class ClientPanelFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new ClientPanelView();
    }
}
