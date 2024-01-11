package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RealDriverDarkMap extends Form implements IMap {
    private final String startPoint;
    private final String destinationPoint;

    public RealDriverDarkMap(String startPoint, String destinationPoint) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }
    private ShowDriverMapController showDriverMapController;

    @Override
    public void displayMap() {
        showDriverMapController.mapView.setMapType(MainStage.getInstance().getProxyMapView().getMapType());
        showDriverMapController.mapView.setZoom(MainStage.getInstance().getProxyMapView().getZoom());
        showDriverMapController.mapView.setCenter(MainStage.getInstance().getProxyMapView().getCenter());
        showDriverMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());
    }

    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DriverMapDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        showDriverMapController = fxmlLoader.getController();
        displayMap();
        MainStage.getInstance().setTitle("Mapa");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
