package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.*;

public class ProxyMap implements IMap {
    public static final MapView mapView = new MapView();
    @Override
    public void displayMap() {
        MapType mapType = MapType.OSM;
        mapView.setMapType(mapType);
        mapView.setZoom(14);
        mapView.setCenter(new Coordinate(50.878978332517214, 20.640200536391333));
        mapView.setPrefHeight(468.0d);
        mapView.setPrefWidth(542.0d);
    }
}
