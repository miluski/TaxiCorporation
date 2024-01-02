package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class AddDriverController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;
    public void onHireDriverButtonClicked() throws Exception {
        FormFactory formFactory = new DeleteDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onManageDriversButtonClicked() throws Exception {
        FormFactory formFactory = new DriverPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onLogoutButtonClicked() {
    }
    public void onEndDriverAddButton() {
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }
}
