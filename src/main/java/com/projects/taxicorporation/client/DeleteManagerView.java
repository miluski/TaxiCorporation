package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class DeleteManagerView extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("DeleteManager.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Usuwanie menadżera");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
