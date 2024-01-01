package com.projects.taxicorporation.client;

public class ConcreteLocationFlyweight implements LocationFlyweight {
    private String name;
    private double latitude;
    private double longitude;
    public ConcreteLocationFlyweight(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public void showLocation() {
        System.out.println("Location: " + name + " (" + latitude + ", " + longitude +
                ")");
    }
}
