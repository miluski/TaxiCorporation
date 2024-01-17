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

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

@ExtendWith(ApplicationExtension.class)
class RenameOrDeleteDepartmentControllerTest {

    private RenameOrDeleteDepartmentController controller;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RenameOrDeleteDepartment.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            controller = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        assertNotNull(controller.departmentNameField);
        assertNotNull(controller.departmentCityField);
        assertNotNull(controller.departmentStreetField);
        assertNotNull(controller.departmentChoiceBox);
    }

    @Test
    void validateDepartmentName_WithValidData_ShouldReturnTrue() {
        controller.departmentNameField.setText("Department123");
        assertTrue(controller.validateDepartmentName());
    }

    @Test
    void validateDepartmentName_WithInvalidData_ShouldReturnFalse() {
        controller.departmentNameField.setText("Dep");
        assertFalse(controller.validateDepartmentName());
    }

    @Test
    void communicateWithServer_ShouldHandleCommunication(FxRobot robot) {
        verifyThat("#departmentNameField", hasText(""));
        verifyThat("#departmentCityField", hasText(""));
        verifyThat("#departmentStreetField", hasText(""));
    }

    @Stop
    private void stop() {
        controller = null;
    }
}
