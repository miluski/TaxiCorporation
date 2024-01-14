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
    private ClientPanelController clientPanelController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientPanel.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            clientPanelController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        // Call the initialize method
        clientPanelController.initialize();

        // Verify that elements are initiated
        assertNotNull(clientPanelController.startPointChoiceBox);
        assertNotNull(clientPanelController.destinationChoiceBox);
        assertNotNull(clientPanelController.buttonsAnchorPane);

        // You can also assert further based on the expected behavior
        // For example, you can check that the choice boxes have the expected items
        assertNotNull(clientPanelController.startPointChoiceBox.getItems());
        assertEquals(8, clientPanelController.startPointChoiceBox.getItems().size());

        assertNotNull(clientPanelController.destinationChoiceBox.getItems());
        assertEquals(8, clientPanelController.destinationChoiceBox.getItems().size());

        // You can also check that default values are set
        assertEquals("Świętokrzyska", clientPanelController.startPointChoiceBox.getValue());
        assertEquals("Warszawska", clientPanelController.destinationChoiceBox.getValue());
    }

    // Stop method to close the test stage
    @Stop
    private void stop() {
        clientPanelController = null;
    }
}
