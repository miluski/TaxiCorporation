package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.framework.junit5.Stop;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(ApplicationExtension.class)
class DeleteCarControllerTest {

    private DeleteCarController deleteCarController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteCar.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            deleteCarController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        deleteCarController.fetchCarsData();
        assertNotNull(deleteCarController.chooseCarChoiceBox);
        assertNotNull(deleteCarController.buttonsAnchorPane);
        assertNotNull(deleteCarController.chooseCarChoiceBox.getItems());
    }

    @Test
    void receiveFeedback_ShouldPopulateChooseCarChoiceBox(FxRobot robot) {
        assertEquals(0, deleteCarController.chooseCarChoiceBox.getItems().size());
    }

    @Stop
    private void stop() {
        deleteCarController = null;
    }
}
