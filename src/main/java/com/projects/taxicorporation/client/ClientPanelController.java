package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

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
        UserOperations userOperations = new UserOperations();
        UserFacade userFacade = new UserFacade(MainStage.getInstance().getUser(), userOperations);
        userFacade.logOutUser();
    }

    public void onEndSearchButtonClicked() {
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
        FormFactory formFactory = new ClientPanelFactory();
        Form form = formFactory.createForm();
        form.start();
    }
}
