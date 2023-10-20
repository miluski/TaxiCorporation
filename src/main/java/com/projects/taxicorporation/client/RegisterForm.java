package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RegisterForm extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(com.projects.taxicorporation.client.RegisterForm.class.getResource("RegisterForm.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Rejestracja");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
