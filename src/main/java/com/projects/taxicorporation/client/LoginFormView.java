package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginFormView extends Form {
    private final ButtonPrototype redButtonPrototype = new RedButtonPrototype();
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("LoginForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        setButtonPrototypesCredentials();
        drawLoginButton(fxmlLoader.getController());
        MainStage.getInstance().setTitle("Logowanie");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
    private void setButtonPrototypesCredentials() throws CloneNotSupportedException {
        redButtonPrototype.setHeight(35.0d);
        redButtonPrototype.setWidth(215.0d);
        redButtonPrototype.setX(325.0d);
        redButtonPrototype.setY(250.0d);
    }
    private void drawLoginButton(LoginFormController loginFormController) {
        ButtonDecorator drawButtonDecorator = new RedButtonDecorator(redButtonPrototype);
        drawButtonDecorator.setText("Zaloguj");
        drawButtonDecorator.draw(loginFormController, loginFormController::onLoginButtonClicked);
    }
}