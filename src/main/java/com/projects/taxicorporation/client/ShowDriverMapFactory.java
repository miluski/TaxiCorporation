package com.projects.taxicorporation.client;

import java.util.Objects;

public class ShowDriverMapFactory implements FormFactory {
    private String startPoint = "";
    private String destinationPoint = "";
    private boolean isRouteStarted = false;
    public ShowDriverMapFactory() {}
    public ShowDriverMapFactory(String startPoint, String destinationPoint, boolean isRouteStarted) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.isRouteStarted = isRouteStarted;
    }
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new RealDriverMap(startPoint, destinationPoint, isRouteStarted) : new RealDriverDarkMap(startPoint, destinationPoint, isRouteStarted);
    }
}
