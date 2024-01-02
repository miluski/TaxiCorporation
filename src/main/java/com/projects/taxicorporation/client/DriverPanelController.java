package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DriverPanelController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;

    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
    public void onAcceptJobButtonClicked() {
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }
}
