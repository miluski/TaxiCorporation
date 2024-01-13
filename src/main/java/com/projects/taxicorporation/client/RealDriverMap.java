package com.projects.taxicorporation.client;

import com.sothawo.mapjfx.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RealDriverMap extends Form implements IMap  {
    private final String startPoint;
    private final String destinationPoint;
    public RealDriverMap(String startPoint, String destinationPoint) {
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

        ConcreteLocationFlyweight location1 = new ConcreteLocationFlyweight(1, "Świętokrzyska", 50.877781, 20.661020);
        ConcreteLocationFlyweight location2 = new ConcreteLocationFlyweight(2, "Warszawska", 50.880063, 20.637531);
        ConcreteLocationFlyweight location3 = new ConcreteLocationFlyweight(3,"Sandomierska", 50.870842, 20.645495);
        ConcreteLocationFlyweight location4 = new ConcreteLocationFlyweight(4, "Piekoszowska", 50.875466, 20.612858);

        // Create Coordinate objects from the locations
        Coordinate coord1 = new Coordinate(location1.latitude, location1.longitude);
        Coordinate coord2 = new Coordinate(location2.latitude, location2.longitude);

        // Create Marker objects and add them to the map
        Marker marker1 = Marker.createProvided(Marker.Provided.BLUE).setPosition(coord1).setVisible(true);
        Marker marker2 = Marker.createProvided(Marker.Provided.BLUE).setPosition(coord2).setVisible(true);

        CoordinateLine track_loc_1_2 =  loadCoordinateLine(getClass().getResource("/M1.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_1_3 =  loadCoordinateLine(getClass().getResource("/M2.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_1_4 =  loadCoordinateLine(getClass().getResource("/M3.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_2_3 =  loadCoordinateLine(getClass().getResource("/M4.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_2_4 =  loadCoordinateLine(getClass().getResource("/M5.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_3_4 =  loadCoordinateLine(getClass().getResource("/M6.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);

        track_loc_1_2.setVisible(true);
        track_loc_1_3.setVisible(true);
        track_loc_1_4.setVisible(true);
        track_loc_2_3.setVisible(true);
        track_loc_2_4.setVisible(true);
        track_loc_3_4.setVisible(true);

        showDriverMapController.mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showDriverMapController.mapView.addMarker(marker1);
                showDriverMapController.mapView.addMarker(marker2);

                // Add the CoordinateLine to the map
                showDriverMapController.mapView.addCoordinateLine(track_loc_1_2);
            }
        });

        showDriverMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());
    }

    private Optional<CoordinateLine> loadCoordinateLine(URL url) {
        try (
                Stream<String> lines = new BufferedReader(
                        new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)).lines()
        ) {
            return Optional.of(new CoordinateLine(
                    lines.map(line -> line.split(";")).filter(array -> array.length == 2)
                            .map(values -> new Coordinate(Double.valueOf(values[0]), Double.valueOf(values[1])))
                            .collect(Collectors.toList())));
        } catch (IOException | NumberFormatException e) {
        }
        return Optional.empty();
    }

    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DriverMap.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        showDriverMapController = fxmlLoader.getController();
        displayMap();
        MainStage.getInstance().setTitle("Mapa");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
