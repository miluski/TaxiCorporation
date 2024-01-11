package com.projects.taxicorporation.client;

import java.util.Objects;

public class ShowDriverMapFactory implements FormFactory {
    private final String startPoint;
    private final String destinationPoint;

    public ShowDriverMapFactory(String startPoint, String destinationPoint) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new RealDriverMap(startPoint, destinationPoint) : new RealDriverDarkMap(startPoint, destinationPoint);
    }
}
