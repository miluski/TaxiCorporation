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
        assertNotNull(loginFormController.loginTextField);
        assertNotNull(loginFormController.buttonsAnchorPane);
        assertNotNull(loginFormController.passwordTextField);
    }

    @Test
    void validatePassword_ShouldReturnFalseForInvalidPassword() {
        loginFormController.passwordTextField.setText("invalidpassword");
        boolean result = loginFormController.validatePassword();
        assertFalse(result);
    }

    @Test
    void validatePassword_ShouldReturnTrueForValidPassword() {
        loginFormController.passwordTextField.setText("ValidPassword123!");
        boolean result = loginFormController.validatePassword();
        assertTrue(result);
    }

    @Stop
    private void stop() {
        loginFormController = null;
    }
}
