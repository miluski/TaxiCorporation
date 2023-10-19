package com.projects.taxicorporation;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterFormController {
    @FXML

    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(com.projects.taxicorporation.HelloApplication.class.getResource("RegisterFormController.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        HelloApplication.mainStage.setTitle("Rejestracja nowego konta");
        HelloApplication.mainStage.setScene(scene);
        HelloApplication.mainStage.show();
    }
}
