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
class ClientPanelControllerTest {
    private static ClientPanelController clientPanelController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ClientPanel.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            clientPanelController = fxmlLoader.getController();
            testStage.setTitle("Panel klienta");
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        clientPanelController.initialize();
        assertNotNull(clientPanelController.startPointChoiceBox);
        assertNotNull(clientPanelController.destinationChoiceBox);
        assertNotNull(clientPanelController.buttonsAnchorPane);
        assertNotNull(clientPanelController.startPointChoiceBox.getItems());
        assertEquals(8, clientPanelController.startPointChoiceBox.getItems().size());
        assertNotNull(clientPanelController.destinationChoiceBox.getItems());
        assertEquals(8, clientPanelController.destinationChoiceBox.getItems().size());
        assertEquals("Świętokrzyska", clientPanelController.startPointChoiceBox.getValue());
        assertEquals("Warszawska", clientPanelController.destinationChoiceBox.getValue());
    }

    @Stop
    private void stop() {
        clientPanelController = null;
    }
}
