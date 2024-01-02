package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ClientPanelController implements Controller {
    @FXML
    private TextField startPointField;

    @FXML
    private TextField destinationField;
    @FXML
    private AnchorPane buttonsAnchorPane;
    public void onMapButtonClicked() throws Exception {
        FormFactory formFactory = new ShowMapFactory();
        Form form = formFactory.createForm();
        form.start();
    }

    public void onFindRouteButtonClicked(MouseEvent mouseEvent) throws Exception {
        String startPoint = startPointField.getText();
        String destinationPoint = destinationField.getText();

        // TODO

        FormFactory chooseRoadFactory = new ChooseRoadFactory();
        Form form = chooseRoadFactory.createForm();
        form.start();
    }

    public void onLogoutButtonClicked() {
    }

    public void onEndSearchButtonClicked() {
    }

    @Override
    public AnchorPane getButtonsAnchorPane() {
        return this.buttonsAnchorPane;
    }
}
