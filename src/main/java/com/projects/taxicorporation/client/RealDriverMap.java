package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RealDriverMap extends Form implements IMap {
    private ShowDriverMapController driverShowMapController;

    @Override
    public void displayMap() {
        driverShowMapController.mapView.setMapType(MainStage.getInstance().getProxyMapView().getMapType());
        driverShowMapController.mapView.setZoom(MainStage.getInstance().getProxyMapView().getZoom());
        driverShowMapController.mapView.setCenter(MainStage.getInstance().getProxyMapView().getCenter());
        driverShowMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());
    }

    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DriverMap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        driverShowMapController = fxmlLoader.getController();
        displayMap();
        MainStage.getInstance().setTitle("Mapa");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
