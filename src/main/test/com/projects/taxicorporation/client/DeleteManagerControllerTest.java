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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class DeleteManagerControllerTest {

    private DeleteMenagerController deleteManagerController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DeleteManager.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            deleteManagerController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        deleteManagerController.fetchManagersData();
        assertNotNull(deleteManagerController.chooseManagerChoiceBox);
        assertNotNull(deleteManagerController.buttonsAnchorPane);
        assertNotNull(deleteManagerController.chooseManagerChoiceBox.getItems());
    }

    @Test
    void receiveFeedback_ShouldPopulateChooseManagerChoiceBox(FxRobot robot) {
        assertEquals(0, deleteManagerController.chooseManagerChoiceBox.getItems().size());
    }

    @Stop
    private void stop() {
        deleteManagerController = null;
    }
}
