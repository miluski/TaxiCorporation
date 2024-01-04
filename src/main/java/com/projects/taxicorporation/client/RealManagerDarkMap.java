package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RealManagerDarkMap extends Form implements IMap {
    private ShowManagerMapController showManagerMapController;

    @Override
    public void displayMap() {
        showManagerMapController.mapView.setMapType(MainStage.getInstance().getProxyMapView().getMapType());
        showManagerMapController.mapView.setZoom(MainStage.getInstance().getProxyMapView().getZoom());
        showManagerMapController.mapView.setCenter(MainStage.getInstance().getProxyMapView().getCenter());
        showManagerMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());
    }

    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ManagerMapDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        showManagerMapController = fxmlLoader.getController();
        displayMap();
        MainStage.getInstance().setTitle("Mapa");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
