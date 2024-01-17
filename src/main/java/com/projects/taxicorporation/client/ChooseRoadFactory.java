package com.projects.taxicorporation.client;

import java.util.Objects;

public class ChooseRoadFactory implements FormFactory {
    private final String startPoint;
    private final String destinationPoint;

    public ChooseRoadFactory(String startPoint, String destinationPoint) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }

    @Override
    public Form createForm() {
        return Objects.equals(MainStage.getInstance().getThemeName(), "Light")
                ? new ChooseRoadView(startPoint, destinationPoint)
                : new ChooseRoadDarkView(startPoint, destinationPoint);
    }
}
