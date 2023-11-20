package com.projects.taxicorporation.client;

public class DriverPanelFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new DriverPanelView();
    }
}
