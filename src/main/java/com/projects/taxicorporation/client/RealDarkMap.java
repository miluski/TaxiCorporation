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

public class RealDarkMap extends Form implements IMap {
    private final String startPoint;
    private final String destinationPoint;
    private final boolean isRouteStarted;
    private final int reservationId;
    private ShowMapController showMapController;
    public RealDarkMap(String startPoint, String destinationPoint, boolean isRouteStarted, int reservationId) {
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.isRouteStarted = isRouteStarted;
        this.reservationId = reservationId;
    }

    @Override
    public void displayMap() {

        showMapController.mapView.setMapType(MainStage.getInstance().getProxyMapView().getMapType());
        showMapController.mapView.setZoom(MainStage.getInstance().getProxyMapView().getZoom());
        showMapController.mapView.setCenter(MainStage.getInstance().getProxyMapView().getCenter());
        showMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());

        Marker startMarker = createMarker(startPoint);
        Marker destinationMarker = createMarker(destinationPoint);

        CoordinateLine correctCoordinateLine = getCorrectCoordinateLine(startPoint, destinationPoint);

        correctCoordinateLine.setVisible(true);

        showMapController.mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                showMapController.mapView.addMarker(startMarker);
                showMapController.mapView.addMarker(destinationMarker);

                showMapController.mapView.addCoordinateLine(correctCoordinateLine);
            }
        });

        showMapController.mapView.initialize(Configuration.builder().projection(Projection.WEB_MERCATOR).build());
    }

    private Marker createMarker(String streetName) {
        ConcreteLocationFlyweight location = switch (streetName) {
            case "Swietokrzyska" -> new ConcreteLocationFlyweight(1, streetName, 50.877781, 20.661020);
            case "Warszawska" -> new ConcreteLocationFlyweight(2, streetName, 50.880063, 20.637531);
            case "Sandomierska" -> new ConcreteLocationFlyweight(3, streetName, 50.870842, 20.645495);
            case "Piekoszowska" -> new ConcreteLocationFlyweight(4, streetName, 50.875466, 20.612858);
            default -> new ConcreteLocationFlyweight(0, "Unknown", 0, 0);
        };

        Coordinate coordinate = new Coordinate(location.latitude, location.longitude);
        return Marker.createProvided(Marker.Provided.BLUE).setPosition(coordinate).setVisible(true);
    }

    private CoordinateLine getCorrectCoordinateLine(String startPoint, String destinationPoint) {
        CoordinateLine track_loc_1_2 = loadCoordinateLine(getClass().getResource("/M1.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_1_3 = loadCoordinateLine(getClass().getResource("/M2.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_1_4 = loadCoordinateLine(getClass().getResource("/M3.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_2_3 = loadCoordinateLine(getClass().getResource("/M4.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_2_4 = loadCoordinateLine(getClass().getResource("/M5.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);
        CoordinateLine track_loc_3_4 = loadCoordinateLine(getClass().getResource("/M6.csv")).orElse(new CoordinateLine()).setColor(Color.MAGENTA);

        if (("Swietokrzyska".equals(startPoint) && "Warszawska".equals(destinationPoint)) || ("Warszawska".equals(startPoint) && "Swietokrzyska".equals(destinationPoint))) {
            return track_loc_1_2;
        } else if (("Swietokrzyska".equals(startPoint) && "Sandomierska".equals(destinationPoint)) || ("Sandomierska".equals(startPoint) && "Swietokrzyska".equals(destinationPoint))) {
            return track_loc_1_3;
        } else if (("Swietokrzyska".equals(startPoint) && "Piekoszowska".equals(destinationPoint)) || ("Piekoszowska".equals(startPoint) && "Swietokrzyska".equals(destinationPoint))) {
            return track_loc_1_4;
        } else if (("Warszawska".equals(startPoint) && "Sandomierska".equals(destinationPoint)) || ("Sandomierska".equals(startPoint) && "Warszawska".equals(destinationPoint))) {
            return track_loc_2_3;
        } else if (("Warszawska".equals(startPoint) && "Piekoszowska".equals(destinationPoint)) || ("Piekoszowska".equals(startPoint) && "Warszawska".equals(destinationPoint))) {
            return track_loc_2_4;
        } else if (("Sandomierska".equals(startPoint) && "Piekoszowska".equals(destinationPoint)) || ("Piekoszowska".equals(startPoint) && "Sandomierska".equals(destinationPoint))) {
            return track_loc_3_4;
        }

        return new CoordinateLine();
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
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ShowMapDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        showMapController = fxmlLoader.getController();
        showMapController.isRouteStarted = this.isRouteStarted;
        showMapController.reservationId = this.reservationId;
        displayMap();
        MainStage.getInstance().setTitle("Mapa");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
