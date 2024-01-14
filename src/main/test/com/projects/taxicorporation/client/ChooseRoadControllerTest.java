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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class ChooseRoadControllerTest {
    private ChooseRoadController chooseRoadController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ChooseRoad.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            chooseRoadController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void setStartAndDestinationPoints_ShouldInitiateElements() {
        chooseRoadController.setStartAndDestinationPoints("StartPoint", "DestinationPoint");

        assertNotNull(chooseRoadController.courseListView);
        assertNotNull(chooseRoadController.getButtonsAnchorPane());

        assertNotNull(chooseRoadController.courseListView.getItems());
        assertEquals(0, chooseRoadController.courseListView.getItems().size());
    }

    @Stop
    private void stop() {
        chooseRoadController = null;
    }
}
