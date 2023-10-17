package com.projects.taxicorporation;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.MapView;
import javafx.fxml.FXML;

public class HelloController {
    @FXML
    private MapView mapView;

    public void initMapAndControls(Projection projection) {
        MapType mapType = MapType.OSM;
        mapView.setMapType(mapType);
        mapView.initialize(Configuration.builder()
            .projection(projection)
            .showZoomControls(false)
            .build());
        mapView.setZoom(14);
        mapView.setCenter(new Coordinate(50.878978332517214, 20.640200536391333));
    }
}