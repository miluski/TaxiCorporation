package com.projects.taxicorporation.client;

import java.util.Objects;

public class ShowMapFactory implements FormFactory {
    private String startPoint = "";
    private String destinationPoint = "";
    private boolean isRouteStarted = false;
    private int reservationId;
    public ShowMapFactory() {}
    public ShowMapFactory(String startPoint, String destinationPoint, boolean isRouteStarted, int reservationId) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.isRouteStarted = isRouteStarted;
        this.reservationId = reservationId;
    }
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new RealMap(startPoint, destinationPoint, isRouteStarted, reservationId) : new RealDarkMap(startPoint, destinationPoint, isRouteStarted, reservationId);
    }
}
