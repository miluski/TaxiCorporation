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

@ExtendWith(ApplicationExtension.class)
class AddDepartmentControllerTest {
    private static AddDepartmentController addDepartmentController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddDepartment.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            addDepartmentController = fxmlLoader.getController();
            testStage.setTitle("Dodawanie oddziału");
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void validateDepartmentName_ValidDepartmentName_ReturnsTrue() {
        addDepartmentController.departmentNameField.setText("ValidDepartmentName");
        assertTrue(addDepartmentController.validateDepartmentName());
    }

    @Test
    void validateDepartmentName_InvalidDepartmentName_ReturnsFalse() {
        addDepartmentController.departmentNameField.setText("12");
        assertFalse(addDepartmentController.validateDepartmentName());
    }

    @Test
    void validateDepartmentCity_ValidDepartmentCity_ReturnsTrue() {
        addDepartmentController.departmentCityField.setText("ValidCity");
        assertTrue(addDepartmentController.validateDepartmentCity());
    }

    @Test
    void validateDepartmentCity_InvalidDepartmentCity_ReturnsFalse() {
        addDepartmentController.departmentCityField.setText("12");
        assertFalse(addDepartmentController.validateDepartmentCity());
    }

    @Test
    void validateDepartmentStreet_ValidDepartmentStreet_ReturnsTrue() {
        addDepartmentController.departmentStreetField.setText("ValidStreet");
        assertTrue(addDepartmentController.validateDepartmentStreet());
    }

    @Test
    void validateDepartmentStreet_InvalidDepartmentStreet_ReturnsFalse() {
        addDepartmentController.departmentStreetField.setText("12");
        assertFalse(addDepartmentController.validateDepartmentStreet());
    }
}
