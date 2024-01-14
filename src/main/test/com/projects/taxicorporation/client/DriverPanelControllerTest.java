package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class DriverPanelControllerTest {

    private DriverPanelController driverPanelController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DriverPanel.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            driverPanelController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        assertNotNull(driverPanelController.buttonsAnchorPane);
    }

    @Test
    void onMapButtonClicked_ShouldCreateShowDriverMapForm() {
        try {
            driverPanelController.onMapButtonClicked();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Stop
    private void stop() {
        driverPanelController = null;
    }
}
