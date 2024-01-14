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
class LoginFormControllerTest {

    private LoginFormController loginFormController;

    @Start
    public void start(Stage testStage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            loginFormController = fxmlLoader.getController();
            testStage.setScene(scene);
            testStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void initialize_ShouldInitiateElements() {
        // Verify that elements are initiated
        assertNotNull(loginFormController.loginTextField);
        assertNotNull(loginFormController.buttonsAnchorPane);
        assertNotNull(loginFormController.passwordTextField);
    }

    @Test
    void validatePassword_ShouldReturnFalseForInvalidPassword() {
        // Set an invalid password
        loginFormController.passwordTextField.setText("invalidpassword");

        // Call validatePassword
        boolean result = loginFormController.validatePassword();

        // Verify that the result is false
        assertEquals(false, result);
    }

    @Test
    void validatePassword_ShouldReturnTrueForValidPassword() {
        // Set a valid password
        loginFormController.passwordTextField.setText("ValidPassword123!");

        // Call validatePassword
        boolean result = loginFormController.validatePassword();

        // Verify that the result is true
        assertEquals(true, result);
    }

    @Stop
    private void stop() {
        loginFormController = null;
    }
}
