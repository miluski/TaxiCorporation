package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RealDarkMap extends Form implements IMap {
    private ShowMapController showMapController;

    @Override
    public void displayMap() {
        showMapController.mapView.setMapType(MainStage.getInstance().getProxyMapView().getMapType());
        showMapController.mapView.setZoom(MainStage.getInstance().getProxyMapView().getZoom());
        showMapController.mapView.setCenter(MainStage.getInstance().getProxyMapView().getCenter());
        showMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());
    }

    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ShowDarkMap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        showMapController = fxmlLoader.getController();
        displayMap();
        MainStage.getInstance().setTitle("Mapa");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
