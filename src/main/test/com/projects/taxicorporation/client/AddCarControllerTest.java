package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(ApplicationExtension.class)
class AddCarControllerTest {
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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void validateCarModel_ValidCarModel_ReturnsTrue() {
        addCarController.carModelField.setText("ValidCarModel");
        assertTrue(addCarController.validateCarModel());
    }

    @Test
    void validateCarModel_InvalidCarModel_ReturnsFalse() {
        addCarController.carModelField.setText("Short");
        assertTrue(addCarController.validateCarModel());
    }

    @Test
    void validateYearOfProduction_ValidYear_ReturnsTrue() {
        addCarController.yearOfProductionField.setText("2022");
        assertTrue(addCarController.validateYearOfProduction());
    }

    @Test
    void validateYearOfProduction_InvalidYear_ReturnsFalse() {
        addCarController.yearOfProductionField.setText("InvalidYear");
        assertFalse(addCarController.validateYearOfProduction());
    }
}
