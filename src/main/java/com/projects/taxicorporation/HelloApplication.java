package com.projects.taxicorporation;

import com.sothawo.mapjfx.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        HelloController controller = fxmlLoader.getController();
        Projection projection = getParameters().getUnnamed().contains("wgs84")? Projection.WGS_84 : Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection);
        stage.setTitle("Test MapJFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}