package com.projects.taxicorporation.client;

public class ChooseRoadFactory implements FormFactory {
    @Override
    public Form createForm() {
        return new ChooseRoad();
    }
}
