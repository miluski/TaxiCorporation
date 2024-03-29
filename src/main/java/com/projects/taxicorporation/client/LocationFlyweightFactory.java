package com.projects.taxicorporation.client;

import java.util.*;

public class LocationFlyweightFactory {
    private Map<String, LocationFlyweight> locationFlyweights = new HashMap<>();
    public LocationFlyweight getLocation(int addressId, String name, double latitude, double
            longitude) {
        String key = name.toLowerCase();
        if (locationFlyweights.containsKey(key)) {
            return locationFlyweights.get(key);
        } else {
            LocationFlyweight location = new ConcreteLocationFlyweight(addressId, name,
                    latitude, longitude);
            locationFlyweights.put(key, location);
            return location;
        }
    }
}

