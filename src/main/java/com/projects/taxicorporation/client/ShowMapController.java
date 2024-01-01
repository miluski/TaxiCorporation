package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.*;
import javafx.fxml.FXML;

public class ShowMapController {
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
    public void onFindRouteButtonClicked() throws Exception {
        FormFactory formFactory = new ClientPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
}
