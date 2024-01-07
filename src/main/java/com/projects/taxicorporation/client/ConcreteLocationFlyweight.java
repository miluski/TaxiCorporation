package com.projects.taxicorporation.client;

public class ConcreteLocationFlyweight implements LocationFlyweight {
    private int addressId;
    private String name;
    private double latitude;
    private double longitude;
    public ConcreteLocationFlyweight(int addressId, String name, double latitude, double longitude)
    {
        this.addressId = addressId;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public void showLocation() {
        System.out.println("Location: " + addressId + name + " (" + latitude + ", " + longitude +
                ")");
    }
}
