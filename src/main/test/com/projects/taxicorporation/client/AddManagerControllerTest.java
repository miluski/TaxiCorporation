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
class AddManagerControllerTest {
    private static AddManagerController addManagerController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddManager.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            addManagerController = fxmlLoader.getController();
            testStage.setTitle("Dodawanie menadżera");
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void validateData_ValidData_ReturnsTrue() {
        addManagerController.nameField.setText("John Doe");
        addManagerController.emailField.setText("john.doe@example.com");
        addManagerController.phoneNumberField.setText("123456789");
        addManagerController.loginField.setText("john_doe");
        addManagerController.passwordField.setText("Password123!");

        assertTrue(addManagerController.validateData());
    }

    @Test
    void validatePassword_ValidPassword_ReturnsTrue() {
        addManagerController.passwordField.setText("Password123!");
        assertTrue(addManagerController.validatePassword());
    }

    @Test
    void validatePassword_InvalidPassword_ReturnsFalse() {
        addManagerController.passwordField.setText("weakpassword");
        assertFalse(addManagerController.validatePassword());
    }
}
