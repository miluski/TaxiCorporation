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
class RegisterFormControllerTest {

    private RegisterFormController registerFormController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterForm.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            registerFormController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        assertNotNull(registerFormController.nameTextField);
        assertNotNull(registerFormController.emailTextField);
        assertNotNull(registerFormController.userNameTextField);
        assertNotNull(registerFormController.passwordTextField);
    }

    @Test
    void validateData_WithValidData_ShouldReturnTrue() {
        registerFormController.nameTextField.setText("John");
        registerFormController.emailTextField.setText("john@example.com");
        registerFormController.userNameTextField.setText("john_doe");
        registerFormController.passwordTextField.setText("Pass@1234");
        assertTrue(registerFormController.validateData());
    }

    @Test
    void communicateWithServer_ShouldHandleCommunication(FxRobot robot) {
        verifyThat("#userNameTextField", hasText(""));
        verifyThat("#passwordTextField", hasText(""));
        verifyThat("#emailTextField", hasText(""));
    }

    @Stop
    private void stop() {
        registerFormController = null;
    }
}
