package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RegisterFormDarkView extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(RegisterFormDarkView.class.getResource("RegisterFormDark.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Rejestracja");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
