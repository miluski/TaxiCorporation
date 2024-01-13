package com.projects.taxicorporation.client;

import java.util.Objects;

public class ShowDriverMapFactory implements FormFactory {
    private final String selectedCourseID;
    private final String startPoint;
    private final String destinationPoint;

    public ShowDriverMapFactory(String selectedCourseID, String startPoint, String destinationPoint) {
        this.selectedCourseID = selectedCourseID;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }
    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light") ? new RealDriverMap(selectedCourseID, startPoint, destinationPoint) : new RealDriverDarkMap(selectedCourseID, startPoint, destinationPoint);
    }
}
