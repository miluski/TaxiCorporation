package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ChooseRoad extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ChooseRoad.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Wybieranie trasy");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
