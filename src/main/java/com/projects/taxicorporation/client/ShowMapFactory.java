package com.projects.taxicorporation.client;

public class ShowMapFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new ShowMapView();
    }
}
