package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class RenameOrDeleteDepartment extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("RenameOrDeleteDepartment.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Zmień nazwę lub usuń oddział");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
