package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DeleteDriverController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;

    public void onAddDriverButtonClicked() throws Exception {
        FormFactory formFactory = new AddDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageDriversButtonClicked() throws Exception {
        FormFactory formFactory = new ManageManagerFactory();
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
    public void onEndHireDriverButtonClicked() {
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }
}
