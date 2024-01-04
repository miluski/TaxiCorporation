package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class DeleteDriverController implements Controller {
    @FXML
    private AnchorPane buttonsAnchorPane;

    public void onAddDriverButtonClicked() throws Exception {
        FormFactory formFactory = new AddDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMenageDriversButtonClicked() throws Exception {
        FormFactory formFactory = new ManageDriversFactory();
        Form form = formFactory.createForm();
        form.start();
    }
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowDriverMapFactory();
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

    public void onChangeThemeButtonClicked() throws Exception {
        if (Objects.equals(MainStage.getInstance().getThemeName(), "Light")) {
            new DarkTheme().applyTheme();
        } else {
            new LightTheme().applyTheme();
        }
        FormFactory formFactory = new DeleteDriverFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
