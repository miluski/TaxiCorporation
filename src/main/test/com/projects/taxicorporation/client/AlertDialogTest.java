package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class AlertDialogTest {

    private static AddCarController addCarController;
    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddCar.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            addCarController = fxmlLoader.getController();
            testStage.setTitle("Dodawanie samochodu");
            testStage.setScene(scene);
            testStage.show();

            AlertDialog.getInstance().setParametersAndShow("Test Message", Alert.AlertType.INFORMATION);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void testAlertDialog() {
        // Assert
        Alert alert = AlertDialog.getInstance().alert;

        assertEquals(Alert.AlertType.INFORMATION, alert.getAlertType());
        assertEquals("Informacja", alert.getTitle());
        assertEquals("Test Message", alert.getHeaderText());
    }
}
