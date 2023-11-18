package com.projects.taxicorporation.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class ClientPanelForm extends Form {
    @Override
    public void start() throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(StartForm.class.getResource("ClientPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainStage.getInstance().setTitle("Panel klienta");
        MainStage.getInstance().setScene(scene);
        MainStage.getInstance().show();
    }
}
