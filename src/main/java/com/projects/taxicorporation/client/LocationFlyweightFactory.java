package com.projects.taxicorporation.client;

import java.util.*;

public class LocationFlyweightFactory {
    private Map<String, LocationFlyweight> locationFlyweights = new HashMap<>();
    public LocationFlyweight getLocation(String name, double latitude, double
            longitude) {
        String key = name.toLowerCase();
        if (locationFlyweights.containsKey(key)) {
            return locationFlyweights.get(key);
        } else {
            // W przeciwnym razie utwórz nowy pyłek i zapisz go w mapie
            LocationFlyweight location = new ConcreteLocationFlyweight(name,
                    latitude, longitude);
            locationFlyweights.put(key, location);
            return location;
        }
    }
}

