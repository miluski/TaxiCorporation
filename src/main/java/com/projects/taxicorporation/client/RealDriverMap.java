package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.Marker;
import com.sothawo.mapjfx.CoordinateLine;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RealDriverMap extends Form implements IMap {

    private final String startPoint;
    private final String destinationPoint;

    private ShowDriverMapController driverShowMapController;

    public RealDriverMap(String startPoint, String destinationPoint) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
    }

    @Override
    public void displayMap() throws MalformedURLException {
        ConcreteLocationFlyweight location1 = new ConcreteLocationFlyweight(1, "Świętokrzyska", 50.877781, 20.661020);
        ConcreteLocationFlyweight location2 = new ConcreteLocationFlyweight(2, "Warszawska", 50.880063, 20.637531);

        driverShowMapController.mapView.setMapType(MainStage.getInstance().getProxyMapView().getMapType());
        driverShowMapController.mapView.setZoom(MainStage.getInstance().getProxyMapView().getZoom());
        driverShowMapController.mapView.setCenter(MainStage.getInstance().getProxyMapView().getCenter());
        driverShowMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());

        // Create markers for start and destination points
        Marker startMarker = createMarker(location1);
        Marker destinationMarker = createMarker(location2);

        // Add markers to the map
        driverShowMapController.mapView.addMarker(startMarker);
        driverShowMapController.mapView.addMarker(destinationMarker);

        // Create a CoordinateLine to connect the start and destination points
        List<Coordinate> coordinates = Arrays.asList(
                new Coordinate(location1.latitude, location1.longitude),
                new Coordinate(location2.latitude, location2.longitude)
        );

        CoordinateLine coordinateLine = new CoordinateLine(coordinates);
        driverShowMapController.mapView.addCoordinateLine(coordinateLine);
    }


    private Marker createMarker(ConcreteLocationFlyweight location) throws MalformedURLException {
        URL imageUrl = new URL("https://png.pngtree.com/png-vector/20210214/ourmid/pngtree-location-marker-png-image_2921053.jpg");

        Marker marker = new Marker(imageUrl);
        marker.setPosition(new Coordinate(location.latitude, location.longitude));
        return marker;
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
