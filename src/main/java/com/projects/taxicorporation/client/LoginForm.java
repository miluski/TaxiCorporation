package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginForm extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("AddManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Logowanie");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
