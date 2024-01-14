package com.projects.taxicorporation.client;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.util.Objects;

public class ClientPanelController implements Controller {
    @FXML
<<<<<<< HEAD
    private TextField startPointField;

    @FXML
    private TextField destinationField;
    @FXML
    private AnchorPane buttonsAnchorPane;
=======
    public ChoiceBox<String> startPointChoiceBox;
    @FXML
    public ChoiceBox<String> destinationChoiceBox;
    @FXML
    public AnchorPane buttonsAnchorPane;

    public void initialize() {
        initializeLocations();
    }

    private void initializeLocations() {
        ConcreteLocationFlyweight location1 = new ConcreteLocationFlyweight(1, "Świętokrzyska", 50.877781, 20.661020);
        ConcreteLocationFlyweight location2 = new ConcreteLocationFlyweight(2, "Warszawska", 50.880063, 20.637531);
        ConcreteLocationFlyweight location3 = new ConcreteLocationFlyweight(3,"Sandomierska", 50.870842, 20.645495);
        ConcreteLocationFlyweight location4 = new ConcreteLocationFlyweight(4, "Piekoszowska", 50.875466, 20.612858);

        startPointChoiceBox.getItems().addAll(location1.name, location2.name, location3.name, location4.name);
        destinationChoiceBox.getItems().addAll(location1.name, location2.name, location3.name, location4.name);

        startPointChoiceBox.setValue("Świętokrzyska");
        destinationChoiceBox.setValue("Warszawska");

        startPointChoiceBox.setOnAction(event -> updateDestinationChoiceBox());
        destinationChoiceBox.setOnAction(event -> updateStartPointChoiceBox());
    }

    private void updateDestinationChoiceBox() {
        String selectedStartPoint = startPointChoiceBox.getValue();
        if (selectedStartPoint != null) {
            destinationChoiceBox.getItems().remove(selectedStartPoint);
        }
    }

    private void updateStartPointChoiceBox() {
        String selectedDestination = destinationChoiceBox.getValue();
        if (selectedDestination != null) {
            startPointChoiceBox.getItems().remove(selectedDestination);
        }
    }

>>>>>>> 9dac993 (add to tests on FE)
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
